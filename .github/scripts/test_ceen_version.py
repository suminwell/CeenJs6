import unittest

from ceen_version import select_version


def release(version, run_number, build_url="https://github.com/example/CeenJs6/actions/runs/old"):
    return {
        "tag_name": f"ceen-v{version}",
        "body": f"- Build: {build_url}\n- Build number: {run_number}\n",
    }


class CeenVersionTests(unittest.TestCase):
    build_url = "https://github.com/example/CeenJs6/actions/runs/current"

    def version(self, base, run_number, releases):
        return select_version(base, run_number, self.build_url, releases)

    def test_first_release_starts_at_one(self):
        self.assertEqual(self.version("6.7.0", 6, []), "6.7.0.1")

    def test_legacy_releases_do_not_become_revision_numbers(self):
        legacy = {"tag_name": "ceen-v6.7.0-ceen.4", "body": ""}
        self.assertEqual(self.version("6.7.0", 6, [legacy]), "6.7.0.1")

    def test_uses_highest_revision_regardless_of_api_order(self):
        history = [release("6.7.0.3", 9), release("6.7.0.1", 6), release("6.7.0.2", 7)]
        self.assertEqual(self.version("6.7.0", 10, history), "6.7.0.4")

    def test_official_update_resets_revision(self):
        history = [release("6.7.0.8", 14)]
        self.assertEqual(self.version("6.9.0", 15, history), "6.9.0.1")
        history.append(release("6.9.0.1", 15))
        self.assertEqual(self.version("6.9.0", 16, history), "6.9.0.2")

    def test_failed_builds_do_not_consume_release_revisions(self):
        history = [release("6.7.0.1", 6)]
        self.assertEqual(self.version("6.7.0", 7, history), "6.7.0.2")
        self.assertEqual(self.version("6.7.0", 8, history), "6.7.0.2")

    def test_repeated_run_keeps_original_version_after_later_releases(self):
        history = [release("6.7.0.1", 6, self.build_url), release("6.7.0.2", 7)]
        self.assertEqual(self.version("6.7.0", 6, history), "6.7.0.1")

    def test_stale_unpublished_run_cannot_publish_lower_android_version_code(self):
        with self.assertRaisesRegex(ValueError, "newer build"):
            self.version("6.7.0", 7, [release("6.9.0.1", 8)])

    def test_repository_rename_preserves_published_run_version(self):
        old_url = "https://github.com/example/PreviousName/actions/runs/123"
        new_url = "https://github.com/example/CeenJs6/actions/runs/123"
        history = [release("6.7.0.1", 6, old_url), release("6.7.0.2", 7)]
        self.assertEqual(select_version("6.7.0", 6, new_url, history), "6.7.0.1")

    def test_similar_version_prefix_is_independent(self):
        history = [release("6.7.01.9", 9), release("16.7.0.5", 10)]
        self.assertEqual(self.version("6.7.0", 11, history), "6.7.0.1")


if __name__ == "__main__":
    unittest.main()

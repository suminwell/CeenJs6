"""Derive CEEN versions from the official version and existing releases."""

import json
import os
from pathlib import Path
import re
import subprocess


def select_version(base, run_number, build_url, releases):
    pattern = re.compile(rf"ceen-v{re.escape(base)}\.(\d+)")
    revisions = []
    rerun_versions = set()
    published_runs = []
    for release in releases:
        tag = release["tag_name"]
        body = release.get("body") or ""
        if match := re.search(r"^- Build number: (\d+)\s*$", body, re.MULTILINE):
            published_runs.append(int(match[1]))
        elif match := re.fullmatch(r"ceen-v\d+\.\d+\.\d+-ceen\.(\d+)", tag):
            # Releases produced before four-part CEEN versions.
            published_runs.append(int(match[1]))
        if match := pattern.fullmatch(tag):
            revisions.append(int(match[1]))
            if f"- Build: {build_url}" in body.splitlines():
                rerun_versions.add(tag.removeprefix("ceen-v"))

    if len(rerun_versions) > 1:
        raise ValueError("Multiple versions refer to this workflow run")
    if rerun_versions:
        return rerun_versions.pop()
    if run_number <= max(published_runs, default=0):
        raise ValueError("A newer build is already published; start a new workflow run instead of publishing an old failed run")
    return f"{base}.{max(revisions, default=0) + 1}"


def main():
    path = Path("version.properties")
    lines = path.read_text().splitlines()
    props = dict(line.split("=", 1) for line in lines if "=" in line and not line.startswith("#"))
    base = props["VERSION_NAME"]
    if not re.fullmatch(r"\d+\.\d+\.\d+", base):
        raise ValueError("VERSION_NAME must contain the three-part official version, for example 6.9.0")
    run_number = int(os.environ["GITHUB_RUN_NUMBER"])
    repository = os.environ["GITHUB_REPOSITORY"]
    build_url = f"{os.environ['GITHUB_SERVER_URL']}/{repository}/actions/runs/{os.environ['GITHUB_RUN_ID']}"
    pages = json.loads(subprocess.check_output([
        "gh", "api", "--paginate", "--slurp", f"repos/{repository}/releases?per_page=100",
    ], text=True))
    releases = [release for page in pages for release in page]
    version = select_version(base, run_number, build_url, releases)
    values = {"VERSION_NAME": version, "VERSION_BUILD": str(1000000 + run_number)}
    path.write_text("\n".join(
        f"{key}={values[key]}" if (key := line.split("=", 1)[0]) in values else line
        for line in lines
    ) + "\n")
    with open(os.environ["GITHUB_OUTPUT"], "a") as output:
        output.write(f"version={version}\ntag=ceen-v{version}\nupstream_version={base}\n")
    print(f"CEEN version: {version}; Android versionCode: {values['VERSION_BUILD']}")


if __name__ == "__main__":
    main()

@file:Suppress("unused")

package org.ceenjs.build

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A Gradle plugin that provides version management functionality.
 *
 * zh-CN: Gradle 版本管理插件.
 *
 * - `id`: "org.ceenjs.build.versions"
 * - `implementationClass`: "org.ceenjs.build.VersionsPlugin"
 * - `displayName`: "CeenJs6 Versions Plugin"
 * - `description`: "Provides version helpers."
 *
 * Apply this plugin to your Android module's `build.gradle.kts`:
 *
 * zh-CN: 在 Android 模块的 `build.gradle.kts` 中应用此插件:<br>
 *
 * ```kts
 * plugins {
 *     id("org.ceenjs.build.versions")
 * }
 *
 * versions.appVersionName
 * versions.appVersionCode
 * versions.sdkVersionCompile
 * versions.sdkVersionMin
 * versions.sdkVersionTarget
 * versions.sdkVersionTargetInrt
 * versions.vscodeExtRequiredVersion
 * ```
 */
class VersionsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.add("versions", Utils.newVersions(project))
    }
}

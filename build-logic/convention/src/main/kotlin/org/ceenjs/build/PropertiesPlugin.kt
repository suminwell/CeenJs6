@file:Suppress("unused")

package org.ceenjs.build

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A Gradle plugin that provides properties helpers.
 *
 * zh-CN: Gradle properties 辅助工具.
 *
 * - `id`: "org.ceenjs.build.properties"
 * - `implementationClass`: "org.ceenjs.build.PropertiesPlugin"
 * - `displayName`: "CeenJs6 Properties Plugin"
 * - `description`: "Provides properties helpers."
 *
 * Apply this plugin to your Android module's `build.gradle.kts`:
 *
 * zh-CN: 在 Android 模块的 `build.gradle.kts` 中应用此插件:<br>
 *
 * ```kts
 * plugins {
 *     id("org.ceenjs.build.properties")
 * }
 *
 * props["MIN_SDK"]
 * props["COMPILE_SDK"]
 * props["TARGET_SDK"]
 *
 * props["RAPID_OCR/NDK"]
 * props["RAPID_OCR/CMAKE"]
 *
 * props["PADDLE_OCR/NDK"]
 * props["PADDLE_OCR/CMAKE"]
 * props["PADDLE_OCR/OPENCV"]
 *
 * props["IMAGE_QUANT/NDK"]
 * props["IMAGE_QUANT/CMAKE"]
 * ```
 */
class PropertiesPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.add("props", Utils.newProperties(project))
    }
}

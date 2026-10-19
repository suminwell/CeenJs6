import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl` /* kotlin("jvm") */
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(gradleApi())

    implementation(libs.apache.commons.compress)
    implementation(libs.tukaani.xz)
}

gradlePlugin {
    plugins {
        register("utils") {
            id = "org.ceenjs.build.utils"
            implementationClass = "org.ceenjs.build.UtilsPlugin"
            displayName = "CeenJs6 Build Utils Plugin"
            description = "Provides utilities for downloading, extracting archives, and version helpers."
        }
        register("versions") {
            id = "org.ceenjs.build.versions"
            implementationClass = "org.ceenjs.build.VersionsPlugin"
            displayName = "CeenJs6 Versions Plugin"
            description = "Provides version helpers."
        }
        register("signs") {
            id = "org.ceenjs.build.signs"
            implementationClass = "org.ceenjs.build.SignsPlugin"
            displayName = "CeenJs6 Signs Plugin"
            description = "Provides signing helpers."
        }
        register("properties") {
            id = "org.ceenjs.build.properties"
            implementationClass = "org.ceenjs.build.PropertiesPlugin"
            displayName = "CeenJs6 Properties Plugin"
            description = "Provides properties helpers."
        }
        register("jvmConvention") {
            id = "org.ceenjs.build.jvm-convention"
            implementationClass = "org.ceenjs.build.JvmConventionPlugin"
            displayName = "CeenJs6 JVM Convention Plugin"
            description = "Configures Java/Kotlin targets for Android modules using central Versions."
        }
        register("localAarRegisterConvention") {
            id = "org.ceenjs.build.local-arr-register-convention"
            implementationClass = "org.ceenjs.build.LocalAarRegisterConventionPlugin"
            displayName = "CeenJs6 Local AAR Register Convention Plugin"
            description = "Provides local AAR register helpers."
        }
    }
}

System.getProperty("gradle.java.version.select").toInt().let { jdk ->
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(jdk))
        }
    }
    kotlin {
        jvmToolchain(jdk)
    }
}

System.getProperty("gradle.jvm.target.effective").let { jvm ->
    java {
        sourceCompatibility = JavaVersion.toVersion(jvm)
        targetCompatibility = JavaVersion.toVersion(jvm)
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(jvm))
        }
    }
}

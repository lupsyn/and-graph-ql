package com.ebdz.buildsrc

import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.ebdz.buildsrc.tools.kotlinOptions
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryKotlinConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("kotlin-android")
            pluginManager.apply("kotlin-kapt")
            pluginManager.apply("kotlin-parcelize")
            val extension = extensions.getByType<LibraryExtension>()
            configureKotlinAndroid(extension)
        }
    }

    private fun Project.configureKotlinAndroid(
        extension: CommonExtension<*, *, *, *, *>
    ) {
        extension.apply {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_17.toString()
            }
        }

        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        dependencies {
            add("implementation", libs.findBundle("kotlin").get())
        }
    }

}

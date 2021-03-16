package com.fevziomurtekin.deezerclonecompose.buildsrc

object Plugins {
    const val android_application = "com.android.application"
    const val kotlin_android = "kotlin-android"
}

object AppInfo {
    const val compileVersion = 30
    const val applicationId = "com.fevziomurtekin.deezerclonecompose"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val proguard_txt = "proguard-android-optimize.txt"
    const val proguard_rules = "proguard-rules.pro"
    const val jvmTarget = "1.8"
    const val baseUrl = "https://api.deezer.com/"
    const val databaseName = "deezerDB"
    const val compose_version = "1.0.0-beta01"
    const val kotlinCompilerVersion = "1.4.30"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0-alpha08"

    object Accompanist {
        private const val version = "0.6.0"
        const val coil = "dev.chrisbanes.accompanist:accompanist-coil:$version"
    }

    object Kotlin {
        private const val version = "1.4.30"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"

        object Coroutines {
            private const val version = "1.4.2"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object Network {
        private const val retrofit_version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
        const val retrofit_converter = "com.squareup.retrofit2:converter-moshi:$retrofit_version"

        private const val moshi_version = "1.11.0"
        const val moshi =  "com.squareup.moshi:moshi:$moshi_version"
        const val moshi_kotlin =  "com.squareup.moshi:moshi-kotlin:$moshi_version"

        private const val okhttp_version = "4.9.0"
        const val okhttp = "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
    }

    object AndroidX {

        private const val ktx_version = "1.3.2"
        const val ktx = "androidx.core:core-ktx:$ktx_version"

        private const val material_version = "1.3.0"
        const val material = "com.google.android.material:material:$material_version"

        private const val appcompat_version = "1.2.0"
        const val appcompat = "androidx.appcompat:appcompat:$appcompat_version"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0-alpha03"
        }

        object Compose {
            const val snapshot = ""
            const val version = "1.0.0-beta01"

            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val material = "androidx.compose.material:material:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val animation = "androidx.compose.animation:animation:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
        }

        object Lifecycle {
            private const val version = "2.3.0"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha02"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        object Test {
            private const val version = "1.2.0"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"
            object Ext {
                private const val version = "1.1.2-rc01"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }
            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
        }
    }

    object Hilt {
        private const val version = "2.31.2-alpha"
        private const val android_version = "1.0.0-alpha02"

        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:$android_version"
        const val android_compiler = "androidx.hilt:hilt-lifecycle-viewmodel:$android_version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
    }

    object JUnit {
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }

    object Exoplayer {
        private const val exoplayer_version = "2.12.0"
        const val exoplayer_core = "com.google.android.exoplayer:exoplayer-core:$exoplayer_version"
        const val exoplayer_dash = "com.google.android.exoplayer:exoplayer-dash:$exoplayer_version"
        const val exoplayer_ui = "com.google.android.exoplayer:exoplayer-ui:$exoplayer_version"
    }

    object Local {
        private const val version = "2.3.0-beta03"
        const val room = "androidx.room:room-runtime:$version"
        const val room_ktx = "androidx.room:room-ktx:$version"
        const val room_compiler = "androidx.room:room-compiler:$version"
        const val room_testing = "androidx.room:room-testing:$version"
    }

    object Detekt {
        const val version = "1.16.0"
        const val detekt = "io.gitlab.arturbosch.detekt"
    }

    object Timber {
        private const val timber_version = "4.7.1"
        const val android = "com.jakewharton.timber:timber:$timber_version"
    }
}


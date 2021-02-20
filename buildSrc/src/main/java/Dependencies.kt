object Gradles{
    private val gradle_version = "7.0.0-alpha06"
    val gradle = "com.android.tools.build:gradle:$gradle_version"

    private val kotlin_gradle_version = "1.4.21"
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_gradle_version"

    private val hilt_version = "2.31.2-alpha"
    val hilt = "com.google.dagger:hilt-android-gradle-plugin:$hilt_version"
}

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
}

object Libraries {
    const val compose_version = "1.0.0-alpha08"
    const val kotlinCompilerVersion = "1.4.21"

    private val ktx_version = "1.3.2"
    val ktx = "androidx.core:core-ktx:$ktx_version"

    private val appcompat_version = "1.2.0"
    val appcompat = "androidx.appcompat:appcompat:$appcompat_version"

    private val material_version = "1.2.1"
    val material = "com.google.android.material:material:$material_version"

    // Compose
    val compose_ui = "androidx.compose.ui:ui:$compose_version"
    val compose_material = "androidx.compose.material:material:$compose_version"
    val compose_tooling = "androidx.compose.ui:ui-tooling:$compose_version"

    // Ktx
    private val ktx_runtime_version = "2.3.0-alpha06"
    val ktx_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$ktx_runtime_version"

    // Tests
    private val junit_version = "4.+"
    val junit = "junit:junit:$junit_version"
    private val junit_ext_version = "1.1.2"
    val junit_ext = "androidx.test.ext:junit:$junit_ext_version"
    private val espresso_core_version = "3.3.0"
    val espresso = "androidx.test.espresso:espresso-core:$espresso_core_version"

    // Hilt
    private val hilt_version = "2.28-alpha"
    val hilt = "com.google.dagger:hilt-android:$hilt_version"
    val hilt_compiler = "com.google.dagger:hilt-android-compiler:$hilt_version"

    // Coroutines
    private val coroutines_version = "1.3.9"
    val coroutines_test     = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version"
    val coroutines_android  = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
    val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"

    // Timber
    private val timber_version = "4.7.1"
    val timber = "com.jakewharton.timber:timber:$timber_version"

    // Pagging
    private val pagging_version = "1.0.0"
    val pagging = "android.arch.paging:runtime:$pagging_version"

    // Room
    private val room_version = "2.2.5"
    val room = "androidx.room:room-runtime:$room_version"
    val room_ktx = "androidx.room:room-ktx:$room_version"
    val room_compiler = "androidx.room:room-compiler:$room_version"
    val room_testing = "androidx.room:room-testing:$room_version"

    // Recyclerview
    private val recyclerview_version = "1.0.0"
    val recyclerview = "androidx.recyclerview:recyclerview:$recyclerview_version"

    // Retrofit & Okhttp
    private val retrofit2_version = "2.9.0"
    private val okhttp_version = "3.12.0"
    private val okhttp_logging_version = "3.9.1"
    val retrofit = "com.squareup.retrofit2:retrofit:$retrofit2_version"
    val retrofit_converter = "com.squareup.retrofit2:converter-gson:$retrofit2_version"
    val retrofit_adapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofit2_version"
    val okhttp = "com.squareup.okhttp3:okhttp:$okhttp_version"
    val okhtpp_logging = "com.squareup.okhttp3:logging-interceptor:$okhttp_logging_version"

    // Exoplayer
    private val exoplayer_version = "2.12.0"
    val exoplayer_core = "com.google.android.exoplayer:exoplayer-core:$exoplayer_version"
    val exoplayer_dash = "com.google.android.exoplayer:exoplayer-dash:$exoplayer_version"
    val exoplayer_ui = "com.google.android.exoplayer:exoplayer-ui:$exoplayer_version"

    private val detekt_version = "1.15.0-RC1"
    val detekt = "io.gitlab.arturbosch.detekt"



}
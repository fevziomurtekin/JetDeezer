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
}

object Libraries {
    const val compose_version = "1.0.0-alpha08"
    const val kotlinCompilerVersion = "1.4.21"

    private val ktx_version = "1.3.2"
    val ktx = "androidx.core:core-ktx:$ktx_version"

    private val appcopat_version = "1.2.0"
    val appcompat = "androidx.appcompat:appcompat:$appcopat_version"

    private val material_version = "1.2.1"
    val material = "com.google.android.material:material:$material_version"

    val compose_ui = "androidx.compose.ui:ui:$compose_version"
    val compose_material = "androidx.compose.material:material:$compose_version"
    val compose_tooling = "androidx.compose.ui:ui-tooling:$compose_version"

    private val ktx_runtime_version = "2.3.0-alpha06"
    val ktx_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$ktx_runtime_version"

    private val junit_version = "4.+"
    val junit = "junit:junit:$junit_version"

    private val junit_ext_version = "1.1.2"
    val junit_ext = "androidx.test.ext:junit:$junit_ext_version"

    private val espresso_core_version = "3.3.0"
    val espresso = "androidx.test.espresso:espresso-core:$espresso_core_version"
}
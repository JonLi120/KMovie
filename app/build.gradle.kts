import org.jetbrains.kotlin.konan.properties.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.hilt)
  alias(libs.plugins.ksp)
}

val properties = Properties().apply {
  val file = rootProject.file("local.properties")
  if (file.exists()) {
    load(file.inputStream())
  }
}

fun getLocalProperty(key: String): String {
  return properties.getProperty(key) ?: throw GradleException("Missing '$key' in local.properties")
}

android {
  namespace = "com.messon.project.kmovie"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.messon.project.kmovie"
    minSdk = 24
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    debug {
      val accessToken: String = getLocalProperty("tmdb_access_token")
      val apiKey: String = getLocalProperty("tmdb_apikey")
      buildConfigField("String", "TMDB_ACCESS_TOKEN", accessToken)
      buildConfigField("String", "TMDB_API_KEY", apiKey)
    }
    release {
      val accessToken: String = getLocalProperty("tmdb_access_token")
      val apiKey: String = getLocalProperty("tmdb_apikey")
      buildConfigField("String", "TMDB_ACCESS_TOKEN", accessToken)
      buildConfigField("String", "TMDB_API_KEY", apiKey)

      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    buildConfig = true
    compose = true
    aidl = false
    shaders = false
  }
}

dependencies {
  coreLibraryDesugaring(libs.core.jdk.desugaring)

  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)

  // Core Android dependencies
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.kotlin.stdlib)

  // Hilt Dependency Injection
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)

  // Components
  implementation(libs.bundles.androidx.compose)
  implementation(libs.bundles.coil)

  // Retrofit
  implementation(libs.bundles.retrofit)
  implementation(libs.kotlinx.serialization.json)

  //Logger
  implementation(libs.timber)

  // Local tests: jUnit, coroutines, Android runner
  testImplementation(libs.junit)
  testImplementation(libs.kotlinx.coroutines.test)
}
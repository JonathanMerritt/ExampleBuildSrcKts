import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import org.gradle.internal.impldep.com.amazonaws.PredefinedClientConfigurations.defaultConfig

/*
 *     Copyright 2018 Jonathan Merritt
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

plugins {
  id("com.android.application")
}

android {
  compileSdkVersion("android-P")
  buildToolsVersion("28.0.0-rc2")

  defaultConfig {
    applicationId = "com.github.jonathanmerritt.examplebuildsrckts"
    minSdkVersion(17)
    targetSdkVersion(27)
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }

  sourceSets {
    getByName("main").java.srcDir("src/main/kotlin")
    getByName("test").java.srcDir("src/test/kotlin")
    getByName("androidTest").java.srcDir("src/androidTest/kotlin")
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
}

implementations(
    Androidx.appcompat,
    Androidx.viewpager,
    Androidx.coordinatorlayout,
    Androidx.Constraintlayout.core,
    Androidx.Constraintlayout.solver,
    Androidx.material,

    Rxjava.core,
    Rxjava.rxandroid,

    Kotlin.stdlib
)

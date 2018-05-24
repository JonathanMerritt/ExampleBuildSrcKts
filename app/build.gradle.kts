import com.android.build.gradle.ProguardFiles.getDefaultProguardFile

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
  compileSdkVersion(27)

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
    getByName("release"){
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
}

dependencies {
  implementation("com.android.support:appcompat-v7:27.1.1")
  testImplementation("junit:junit:4.12")
  androidTestImplementation("com.android.support.test:runner:1.0.2")
  androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}

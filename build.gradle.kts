import java.nio.file.Files.delete

buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:3.2.0-alpha15")
    classpath(Kotlin.gradle_plugin())
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}

tasks { "clean" { delete(rootProject.buildDir) } }

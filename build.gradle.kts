import java.nio.file.Files.delete

buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath(Android.gradle())
    classpath(Kotlin.gradle())
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}

tasks { "clean" { delete(rootProject.buildDir) } }

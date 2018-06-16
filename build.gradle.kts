import java.nio.file.Files.delete

buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath(Com.Build.gradle())

    classpath(Kotlin.gradle_plugin())
  }
}

subprojects {
  repositories {
    google()
    jcenter()
  }
}

tasks { "clean" { delete(rootProject.buildDir) } }

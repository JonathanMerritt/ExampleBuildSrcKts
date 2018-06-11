import java.nio.file.Files.delete

buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath(Tools.gradle())

    classpath(Jetbrains.kotlin_gradle_plugin())
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}

tasks { "clean" { delete(rootProject.buildDir) } }

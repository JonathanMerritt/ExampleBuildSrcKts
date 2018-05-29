import Dependency.Info.Artifact
import Dependency.Info.Group
import Dependency.Info.Version

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

private object Groups {
  internal val androidx = Group("androidx")
  internal val reactivex = Group("io.reactivex.rxjava2")
  internal val jetbrains = Group("org.jetbrains")
}

private object Artifacts {
  internal val constraintlayout = Group.Artifact("constraintlayout")
  internal val kotlin = Group.Artifact("kotlin")
}

private object Versions {
  internal val gradle = Version("3.2.0-alpha15")
  internal val appcompat = Version("1.0.0-alpha1")
  internal val viewpager = Version("1.0.0-alpha1")
  internal val coordinatorlayout = Version("1.0.0-alpha1")
  internal val constraintlayout = Version("1.1.0")
  internal val material = Version("1.0.0-alpha1")
  internal val rxjava = Version("2.1.7")
  internal val rxandroid = Version("2.0.1")
  internal val kotlin = Version("1.2.41")
}

@Suppress("unused")
object Android {
  val gradle = Dependency(Group("com.android.tools.build"), Artifact("gradle"), Versions.gradle).id
}

@Suppress("unused")
object Androidx {
  val appcompat = Dependency(Groups.androidx, Group.Artifact("appcompat"), Versions.appcompat).id
  val viewpager = Dependency(Groups.androidx, Group.Artifact("viewpager"), Versions.viewpager).id
  val coordinatorlayout = Dependency(Groups.androidx, Group.Artifact("coordinatorlayout"),
      Versions.coordinatorlayout).id

  object Constraintlayout {
    val core = Dependency(Groups.androidx, Artifacts.constraintlayout, Versions.constraintlayout).id
    val solver = Dependency(Groups.androidx, Artifacts.constraintlayout("solver"), Versions.constraintlayout).id
  }
}

@Suppress("unused")
object Google {
  val material = Dependency(Group("com.google.android"), Group.Artifact("material"), Versions.material).id
}

@Suppress("unused")
object Rxjava {
  val core = Dependency(Groups.reactivex, Artifact("rxjava"), Versions.rxjava).id
  val rxandroid = Dependency(Groups.reactivex, Artifact("rxandroid"), Versions.rxandroid).id
}

@Suppress("unused")
object Kotlin {
  val stdlib = Dependency(Groups.jetbrains, Artifacts.kotlin("stdlib-jdk8"), Versions.kotlin).id
  val gradle = Dependency(Groups.jetbrains, Artifacts.kotlin("gradle-plugin"), Versions.kotlin).id
}

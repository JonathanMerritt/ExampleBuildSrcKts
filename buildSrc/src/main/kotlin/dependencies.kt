import Dependency.Info.Artifact
import Dependency.Info.Feature
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
}

private object Versions {
  internal val androidx = Version("1.0.0-alpha1")
  internal val constraintlayout = Version("1.1.0")
  internal val rxjava = Version("2.1.7")
  internal val rxandroid = Version("2.0.1")
  internal val kotlin = Version("1.2.41")
}

@Suppress("unused")
object Androidx : Dependency(Groups.androidx, version = Versions.androidx) {
  val appcompat = this + Artifact("appcompat", true)
  val viewpager = this + Artifact("viewpager", true)
  val coordinatorlayout = this + Artifact("coordinatorlayout", true)

  object Constraintlayout : Dependency(Groups.androidx, Artifact("constraintlayout", true),
      version = Versions.constraintlayout) {
    val solver = this + Feature("solver")
  }

  val material = this + Group("com.google.android") + Artifact("material", true)
}

@Suppress("unused")
object Rxjava : Dependency(Group("io.reactivex.rxjava2"), Artifact("rxjava"), version = Versions.rxjava) {
  val rxandroid = this + Artifact("rxandroid") + Versions.rxandroid
}

@Suppress("unused")
object Kotlin : Dependency(Group("org.jetbrains"), Artifact("kotlin", true), version = Versions.kotlin) {
  val stdlib_jdk8 = this + Feature("stdlib-jdk8")
  val gradle_plugin = this + Feature("gradle-plugin")
}

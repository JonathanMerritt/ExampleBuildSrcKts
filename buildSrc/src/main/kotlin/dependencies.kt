import Dependency.Info.Artifact.Type.GROUP
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

private object Versions {
  internal val gradle = Version("3.2.0-alpha16")
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
object Android : Group("com.android.tools.build") {
  val gradle = this(Artifact("gradle", Versions.gradle))
}

@Suppress("unused")
object Androidx : Group("androidx") {
  val appcompat = this(Artifact("appcompat", Versions.appcompat, GROUP))
  val viewpager = this(Artifact("viewpager", Versions.viewpager, GROUP))
  val coordinatorlayout = this(Artifact("coordinatorlayout", Versions.coordinatorlayout, GROUP))

  object Constraintlayout : Artifact("constraintlayout", Versions.constraintlayout, GROUP) {
    val core = Androidx(this)
    val solver = Androidx(this("solver"))
  }
}

@Suppress("unused")
object Google : Group("com.google.android") {
  val material = this(Artifact("material", Versions.material, GROUP))
}

@Suppress("unused")
object Rxjava : Group("io.reactivex.rxjava2") {
  val core = this(Artifact("rxjava", Versions.rxjava))
  val rxandroid = this(Artifact("rxandroid", Versions.rxandroid))
}

@Suppress("unused")
object Kotlin : Group("org.jetbrains", Artifact("kotlin", Versions.kotlin, GROUP)) {
  val stdlib = this("stdlib-jdk8")
  val gradle = this("gradle-plugin")
}

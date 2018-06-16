import Dependency.Grouping

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

@Suppress("unused")
object Androidx : Grouping("androidx") {
  val appcompat = tagged("appcompat", "1.0.0-alpha3")
  val viewpager = tagged("viewpager", "1.0.0-alpha3")
  val coordinatorlayout = tagged("coordinatorlayout", "1.0.0-alpha3")

  val constraintlayout = tagged("constraintlayout", "1.1.2")
  val constraintlayout_solver = constraintlayout("solver")
}

@Suppress("unused")
object Com : Grouping("com") {
  object Android : Grouping(this, "google.android") {
    val material = tagged("material", "1.0.0-alpha3")
  }

  object Build : Grouping(this, "android.tools.build") {
    val gradle = normal("gradle", "3.2.0-alpha18")
  }
}

@Suppress("unused")
object Kotlin : Grouping("org.jetbrains") {
  private val kotlin = tagged("kotlin", "1.2.50")
  val gradle_plugin = kotlin("gradle-plugin")
  val stdlib_jdk8 = kotlin("stdlib-jdk8")
}

@Suppress("unused")
object Rxjava2 : Grouping("io.reactivex.rxjava2") {
  val rxandroid = normal("rxandroid", "2.0.1")

  val rxjava = normal("rxjava", "2.1.7")
}

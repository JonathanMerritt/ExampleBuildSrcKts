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
object Tools : Grouping("com.android.tools.build") {
  val gradle = this(normal("gradle", "3.2.0-alpha17"))
}

@Suppress("unused")
object Androidx : Grouping("androidx") {
  val appcompat = this(tagged("appcompat", "1.0.0-alpha3"))
  val viewpager = this(tagged("viewpager", "1.0.0-alpha3"))
  val coordinatorlayout = this(tagged("coordinatorlayout", "1.0.0-alpha3"))

  val constraintlayout = this(tagged("constraintlayout", "1.1.1"))
  val constraintlayout_solver = this(constraintlayout.artifact("solver"))
}

@Suppress("unused")
object Google : Grouping("com.google.android") {
  val material = this(tagged("material", "1.0.0-alpha3"))
}

@Suppress("unused")
object Jetbrains : Grouping("org.jetbrains") {
  private val kotlin = tagged("kotlin", "1.2.41")
  val kotlin_gradle_plugin = this(kotlin("gradle-plugin"))
  val kotlin_stdlib_jdk8 = this(kotlin("stdlib-jdk8"))
}

@Suppress("unused")
object Rxjava2 : Grouping("io.reactivex.rxjava2") {
  val rxjava = this(normal("rxjava", "2.1.7"))
  val rxandroid = this(normal("rxandroid", "2.0.1"))
}

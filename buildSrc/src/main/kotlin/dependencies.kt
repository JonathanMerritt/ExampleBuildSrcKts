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
object AndroidTools : Grouping("com.android.tools.build", {
  this + normal("gradle", "3.2.0-alpha17")
})

@Suppress("unused")
object Androidx : Grouping("androidx", {
  this + tagged("appcompat", "1.0.0-alpha3")
  this + tagged("viewpager", "1.0.0-alpha3")
  this + tagged("coordinatorlayout", "1.0.0-alpha3")

  val constraintlayout = this + tagged("constraintlayout", "1.1.1")
  this + constraintlayout.artifact()("solver")
})

@Suppress("unused")
object Google : Grouping("com.google.android", {
  this + tagged("material", "1.0.0-alpha3")
})

@Suppress("unused")
object Kotlin : Grouping("org.jetbrains") {
  private val artifact = tagged("kotlin", "1.2.41")
  val gradle_plugin = this(artifact("gradle-plugin"))

  init {
    this + artifact("stdlib-jdk8")
  }
}

@Suppress("unused")
object Rxjava : Grouping("io.reactivex.rxjava2", {
  this + normal("rxjava", "2.1.7")
  this + normal("rxandroid", "2.0.1")
})

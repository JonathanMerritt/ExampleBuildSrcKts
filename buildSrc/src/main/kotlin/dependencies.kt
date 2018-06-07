
import Android.GRADLE
import Dependency.Grouping.With
import Kotlin.GRADLE_PLUGIN
import Kotlin.STDLIB_JDK8

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

object Android : With("com.android.tools.build", {
  listOf(
      normal(GRADLE, "3.2.0-alpha17"))
}) {
  const val GRADLE = "gradle"
}

object Androidx : With("androidx", {
  val constraintlayout = tagged("constraintlayout", "1.1.1")
  listOf(
      tagged("appcompat", "1.0.0-alpha3"),
      tagged("viewpager", "1.0.0-alpha3"),
      tagged("coordinatorlayout", "1.0.0-alpha3"),
      constraintlayout,
      constraintlayout("solver"))
})

object Google : With("com.google.android", {
  listOf(
      tagged("material", "1.0.0-alpha3"))
})

object Kotlin : With("org.jetbrains", {
  val kotlin = tagged("kotlin", "1.2.41")
  listOf(
      kotlin(GRADLE_PLUGIN),
      kotlin(STDLIB_JDK8))
}) {
  const val GRADLE_PLUGIN = "gradle-plugin"
  const val STDLIB_JDK8 = "stdlib-jdk8"
}

object Rxjava : With("io.reactivex.rxjava2", {
  listOf(
      normal("rxjava", "2.1.7"),
      normal("rxandroid", "2.0.1"))
})

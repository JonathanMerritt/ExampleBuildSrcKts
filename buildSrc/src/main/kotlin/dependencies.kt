
import Android.GRADLE
import Dependency.Grouping
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

@Suppress("unused")
object Android : Grouping("com.android.tools.build", {
  listOf(
      normal(GRADLE, "3.2.0-alpha16"))
}) {
  const val GRADLE = "gradle"
}

@Suppress("unused")
object Androidx : Grouping("androidx", {
  val constraintlayout = tagged("constraintlayout", "1.1.0")
  listOf(
      tagged("appcompat", "1.0.0-alpha1"),
      tagged("viewpager", "1.0.0-alpha1"),
      tagged("coordinatorlayout", "1.0.0-alpha1"),
      constraintlayout,
      constraintlayout.feature("solver"))
})

@Suppress("unused")
object Google : Grouping("com.google.android", {
  listOf(
      tagged("material", "1.0.0-alpha1"))
})

@Suppress("unused")
object Kotlin : Grouping("org.jetbrains", {
  val kotlin = tagged("kotlin", "1.2.41")
  listOf(
      kotlin.feature(STDLIB_JDK8),
      kotlin.feature(GRADLE_PLUGIN))
}) {
  const val GRADLE_PLUGIN = "gradle-plugin"
  const val STDLIB_JDK8 = "stdlib-jdk8"
}

@Suppress("unused")
object Rxjava : Grouping("io.reactivex.rxjava2", {
  listOf(
      normal("rxjava", "2.1.7"),
      normal("rxandroid", "2.0.1"))
})

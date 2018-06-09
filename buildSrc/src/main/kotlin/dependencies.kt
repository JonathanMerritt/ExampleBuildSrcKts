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

object AndroidTools : Grouping("com.android.tools.build", {
  normal("gradle", "3.2.0-alpha17").add()
})

object Androidx : Grouping("androidx", {
  tagged("appcompat", "1.0.0-alpha3").add()
  tagged("viewpager", "1.0.0-alpha3").add()
  tagged("coordinatorlayout", "1.0.0-alpha3").add()

  val constraintlayout = tagged("constraintlayout", "1.1.1").add()
  constraintlayout.artifact()("solver").add()
})

object Google : Grouping("com.google.android", {
  tagged("material", "1.0.0-alpha3").add()
})

object Kotlin : Grouping("org.jetbrains") {
  private val artifact = tagged("kotlin", "1.2.41")
  val gradle_plugin = artifact("gradle-plugin").dependency()

  init {
    artifact("stdlib-jdk8").add()
  }
}

object Rxjava : Grouping("io.reactivex.rxjava2", {
  normal("rxjava", "2.1.7").add()
  normal("rxandroid", "2.0.1").add()
})

import Dependency.Info.Artifact.Tagged
import Dependency.Info.Group

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
object Android : Group("com.android.tools.build") {
  val gradle = addNormal("gradle", "3.2.0-alpha16")
}

@Suppress("unused")
object Androidx : Group("androidx") {
  val appcompat = addTagged("appcompat", "1.0.0-alpha1")
  val viewpager = addTagged("viewpager", "1.0.0-alpha1")
  val coordinatorlayout = addTagged("coordinatorlayout", "1.0.0-alpha1")

  object Constraintlayout : Tagged("constraintlayout", "1.1.0") {
    val core = add(this)
    val solver = add(feature("solver"))
  }
}

@Suppress("unused")
object Google : Group("com.google.android") {
  val material = addTagged("material", "1.0.0-alpha1")
}

@Suppress("unused")
object Rxjava : Group("io.reactivex.rxjava2") {
  val core = addNormal("rxjava", "2.1.7")
  val rxandroid = addNormal("rxandroid", "2.0.1")
}

@Suppress("unused")
object Kotlin : Group("org.jetbrains", Tagged("kotlin", "1.2.41")) {
  val stdlib = addFeature("stdlib-jdk8")
  val gradle = addFeature("gradle-plugin")
}

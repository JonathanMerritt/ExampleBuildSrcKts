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

val appcompat = Dependency.Tagged("androidx", "appcompat", "1.0.0-alpha1")
val viewpager = Dependency.Tagged(appcompat.group, "viewpager", appcompat.version)
val coordinatorlayout = Dependency.Tagged(appcompat.group, "coordinatorlayout", appcompat.version)
val constraintlayout = Dependency.Tagged(appcompat.group, "constraintlayout", "1.1.0")
val constraintlayout_solver = Dependency.Normal(constraintlayout.group_name, "${constraintlayout.name}-solver",
    constraintlayout.version)

val material = Dependency.Tagged("com.google.android", "material", appcompat.version)

val rxjava = Dependency.Normal("io.reactivex.rxjava2", "rxjava", "2.1.7")
val rxandroid = Dependency.Normal(rxjava.group, "rxandroid", "2.0.1")

val kotlin_stdlib_jdk8 = Dependency.Normal("org.jetbrains.kotlin", "kotlin-stdlib-jdk8", "1.2.41")
val kotlin_gradle_plugin = Dependency.Normal(kotlin_stdlib_jdk8.group, "kotlin-gradle-plugin",
    kotlin_stdlib_jdk8.version)

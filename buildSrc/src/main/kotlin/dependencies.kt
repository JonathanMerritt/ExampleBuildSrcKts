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

val appcompat = Dependency.Type1("androidx", "appcompat", "1.0.0-alpha1")
val viewpager = Dependency.Type1(appcompat.group, "viewpager", appcompat.version)
val coordinatorlayout = Dependency.Type1(appcompat.group, "coordinatorlayout", appcompat.version)
val constraintlayout = Dependency.Type1(appcompat.group, "constraintlayout", "1.1.0")
val constraintlayout_solver = Dependency.Type2(constraintlayout.groupName, "${constraintlayout.name}-solver",
    constraintlayout.version)

val material = Dependency.Type1("com.google.android", "material", appcompat.version)

val rxjava = Dependency.Type2("io.reactivex.rxjava2", "rxjava", "2.1.7")
val rxandroid = Dependency.Type2(rxjava.group, "rxandroid", "2.0.1")

val kotlin_stdlib_jdk8 = Dependency.Type2("org.jetbrains.kotlin", "kotlin-stdlib-jdk8", "1.2.41")
val kotlin_gradle_plugin = Dependency.Type2(kotlin_stdlib_jdk8.group, "kotlin-gradle-plugin",
    kotlin_stdlib_jdk8.version)

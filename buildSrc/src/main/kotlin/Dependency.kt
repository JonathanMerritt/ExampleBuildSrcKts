import Dependency.Info.Artifact
import Dependency.Info.Feature
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

open class Dependency(
    private val group: Group = Group(""),
    private val artifact: Artifact = Artifact(""),
    private val feature: Feature = Feature(""),
    private val version: Version = Version("")
) {

  sealed class Info(val id: String) {
    open class Group(id: String) : Info(id)
    open class Artifact(id: String, tag: Boolean = false) : Info((".$id" orEmptyIf tag) + ":$id")
    open class Feature(id: String) : Info("-$id" orEmptyIf id.isNotEmpty()) {
      operator fun invoke(id1: String) = Feature(id.trimStart('-') + "-$id1" orEmptyIf id1.isNotEmpty())
    }

    open class Version(id: String) : Info(":$id")
  }

  operator fun invoke() = group.id + artifact.id + feature.id + version.id

  operator fun plus(it: Info) = when (it) {
    is Group -> Dependency(it, artifact, feature, version)
    is Artifact -> Dependency(group, it, feature, version)
    is Feature -> Dependency(group, artifact, it, version)
    is Version -> Dependency(group, artifact, feature, it)
  }
}

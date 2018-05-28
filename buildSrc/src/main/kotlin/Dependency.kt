
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
    init {

    }
    open class Group(id: String) : Info(id)
    open class Artifact(id: String, tag: Boolean = false) : Info((if (tag) ".$id" else "") + ":$id")
    open class Feature(id: String) : Info(if (id.isNotEmpty()) "-$id" else "") {
      operator fun invoke(id: String) = Feature(this.id.trimStart('-') + "-$id")
    }
    open class Version(id: String) : Info(":$id")
  }

  operator fun invoke() = group.id + artifact.id + feature.id + version.id

  operator fun plus(info: Info) = when (info) {
    is Group -> Dependency(info, artifact, feature, version)
    is Artifact -> Dependency(group, info, feature, version)
    is Feature -> Dependency(group, artifact, info, version)
    is Version -> Dependency(group, artifact, feature, info)
  }
}

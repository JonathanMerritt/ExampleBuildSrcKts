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

open class Dependency(private val group: Group = Group(), private val artifact: Artifact = Artifact(),
    private val feature: Feature = Feature(), private val version: Version = Version()) {

  sealed class Info(private val id: String) {
    operator fun invoke() = id
    operator fun not() = id != ""
    open class Group(id: String = "") : Info(id)
    open class Artifact(id: String = "", internal val addToGroup: Boolean = false) : Info(id)
    open class Feature(id: String = "") : Info(id)
    open class Version(id: String = "") : Info(id)
  }

  operator fun invoke() = "${group().let { if (artifact.addToGroup) "$it.${artifact()}" else it }}:${artifact()
      .let { if (!feature) "$it-${feature()}" else it }}:${version()}"

  operator fun invoke(info: Info) = when (info) {
    is Group -> Dependency(info, artifact, feature, version)
    is Artifact -> Dependency(group, info, feature, version)
    is Feature -> Dependency(group, artifact, info, version)
    is Version -> Dependency(group, artifact, feature, info)
  }

  operator fun invoke(info: Info, info2: Info) = this(info)(info2)
  operator fun invoke(info: Info, info2: Info, info3: Info) = this(info, info2)(info3)
}

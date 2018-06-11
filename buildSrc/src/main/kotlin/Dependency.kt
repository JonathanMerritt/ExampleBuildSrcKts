import Dependency.Info.Artifact
import Dependency.Info.Artifact.Feature
import Dependency.Info.Artifact.Normal
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
data class Dependency(val group: Group, val artifact: Artifact) {
  operator fun invoke() = group.path + artifact.path + artifact.feature.path + artifact.version.path

  sealed class Info(val id: String) {
    val path = run {
      if (id.isEmpty()) "" else when (this) {
        is Group -> ""
        is Tagged -> ".${id.run { indexOf("-").let { if (it > 0) removeRange(it, length) else this } }}:"
        is Feature -> "-"
        else -> ":"
      } + id
    }

    class Group internal constructor(id: String) : Info(id)
    sealed class Artifact(id: String, val version: Version, val feature: Feature = Feature("")) : Info(id) {
      internal operator fun invoke(featureId: String, versionId: String = ""): Artifact = when (this) {
        is Normal -> Normal(id + feature.path,
            versionId.run { if (versionId.isEmpty()) version.id else versionId }, featureId)
        is Tagged -> Tagged(id + feature.path,
            versionId.run { if (versionId.isEmpty()) version.id else versionId }, featureId)
      }

      class Normal internal constructor(id: String, versionId: String, featureId: String = "") : Artifact(id,
          Version(versionId), Feature(featureId))

      class Tagged internal constructor(id: String, versionId: String, featureId: String = "") : Artifact(id,
          Version(versionId), Feature(featureId))

      class Feature internal constructor(id: String) : Info(id)
      class Version internal constructor(id: String) : Info(id)
    }
  }

  open class Grouping(groupId: String = "", private val group: Group = Group(groupId)) {
    internal fun normal(artifactId: String, versionId: String, featureId: String = "") =
        Normal(artifactId, versionId, featureId).asDependency()

    internal fun tagged(artifactId: String, versionId: String, featureId: String = "") =
        Tagged(artifactId, versionId, featureId).asDependency()

    internal operator fun Dependency.invoke(featureId: String, versionId: String = "") = artifact(featureId,
        versionId).asDependency()

    internal fun Artifact.asDependency(groupId: String = "") = Dependency(
        groupId.run { if (isEmpty()) group else Group(this) }, this)
  }
}

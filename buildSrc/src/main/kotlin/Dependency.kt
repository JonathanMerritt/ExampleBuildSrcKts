import Dependency.Info.Artifact
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

data class Dependency(val group: Group, val artifact: Artifact) {
  operator fun invoke() = group.path() + artifact.path() + artifact.feature.path() + artifact.version.path()

  sealed class Info(internal val id: String) {
    fun path() = if (id.isEmpty()) "" else when (this) {
      is Group -> id
      is Artifact.Tagged -> ".${id.run { indexOf("-").let { if (it > 0) removeRange(it, length) else this } }}:$id"
      is Artifact.Feature -> "-$id"
      else -> ":$id"
    }

    class Group internal constructor(id: String) : Info(id)

    sealed class Artifact(id: String, internal val version: Version,
        internal val feature: Feature = Feature("")) : Info(id) {

      operator fun invoke(featureId: String, versionId: String = version.id): Artifact = when (this) {
        is Normal -> Normal(id + feature.path(), versionId, featureId)
        is Tagged -> Tagged(id + feature.path(), versionId, featureId)
      }

      class Normal internal constructor(id: String, versionId: String, featureId: String = "") : Artifact(id,
          Version(versionId), Feature(featureId))

      class Tagged internal constructor(id: String, versionId: String, featureId: String = "") : Artifact(id,
          Version(versionId), Feature(featureId))

      class Feature internal constructor(id: String) : Info(id)

      class Version internal constructor(id: String) : Info(id)
    }
  }

  open class Grouping(private val groupId: String = "", init: Grouping.() -> Unit = { }) {
    private val dependencies: HashMap<String, Dependency> = hashMapOf()

    operator fun invoke(dependency: (Dependency) -> Unit) = dependencies.forEach { dependency(it.value) }
    operator fun invoke(artifactId: String) = dependencies[artifactId]!!

    init {
      apply { init(this) }
    }

    fun Artifact.dependency(groupId: String = this@Grouping.groupId) = Dependency(Group(groupId), this)
    fun Artifact.add(groupId: String = this@Grouping.groupId) = dependency(
        groupId).also { dependencies[feature.id.run { if (isEmpty()) id else this }] = it }

    fun normal(artifactId: String, versionId: String, featureId: String = "") = Normal(artifactId, versionId,
        featureId)

    fun tagged(artifactId: String, versionId: String, featureId: String = "") = Tagged(artifactId, versionId,
        featureId)
  }
}

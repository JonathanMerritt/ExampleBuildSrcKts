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

@Suppress("unused")
data class Dependency(private val group: Group, private val artifact: Artifact) {

  operator fun invoke() = group().path() + artifact().path() + artifact().feature().path() + artifact().version().path()

  fun group() = group
  fun artifact() = artifact

  sealed class Info(private val id: String) {

    fun id() = id
    fun path() = if (id.isEmpty()) "" else when (this) {
      is Group -> ""
      is Artifact.Tagged -> ".${id.run { indexOf("-").let { if (it > 0) removeRange(it, length) else this } }}:"
      is Artifact.Feature -> "-"
      else -> ":"
    } + id

    class Group internal constructor(id: String) : Info(id)

    sealed class Artifact(id: String, private val version: Version, private val feature: Feature = Feature(""))
      : Info(id) {

      fun feature() = feature
      fun version() = version

      internal operator fun invoke(featureId: String, versionId: String = version().id()): Artifact = when (this) {
        is Normal -> Normal(id() + feature().path(), versionId, featureId)
        is Tagged -> Tagged(id() + feature().path(), versionId, featureId)
      }

      class Normal internal constructor(id: String, versionId: String, featureId: String = "")
        : Artifact(id, Version(versionId), Feature(featureId))

      class Tagged internal constructor(id: String, versionId: String, featureId: String = "")
        : Artifact(id, Version(versionId), Feature(featureId))

      class Feature internal constructor(id: String) : Info(id)
      class Version internal constructor(id: String) : Info(id)
    }
  }

  open class Grouping(groupId: String = "", init: Grouping.() -> Unit = { },
      private val group: Group = Group(groupId),
      private val dependencies: HashMap<String, Dependency> = hashMapOf()) {

    init {
      apply { init(this) }
    }

    operator fun invoke(dependency: (Dependency) -> Unit) = dependencies.forEach { dependency(it.value) }
    operator fun invoke(artifactId: String) = dependencies[artifactId]!!

    internal operator fun invoke(artifact: Artifact) = artifact.make()
    internal operator fun plus(artifact: Artifact) = artifact.plus()
    internal fun Artifact.make(groupId: String = "") = Dependency(
        groupId.run { if (isEmpty()) group else Group(this) }, this)

    internal fun Artifact.plus(groupId: String = "") = make(groupId).also {
      dependencies[feature().id().let { if (it.isEmpty()) id() else it }] = it
    }

    internal fun normal(artifactId: String, versionId: String, featureId: String = "") =
        Normal(artifactId, versionId, featureId)

    internal fun tagged(artifactId: String, versionId: String, featureId: String = "") =
        Tagged(artifactId, versionId, featureId)
  }
}

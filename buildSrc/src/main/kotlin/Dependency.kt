
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
data class Dependency(private val group: Group, private val artifact: Artifact) : ToString {
  override fun invoke() = "${group()}${artifact()}"

  sealed class Info(private val id: String) : ToString {
    override fun invoke() = id
    fun id() = id

    class Group internal constructor(id: String) : Info(id) {
      fun normal(id: String, version: String, feature: String = "") = Normal(id, version, feature)
      fun tagged(id: String, version: String, feature: String = "") = Tagged(id, version, feature)
    }

    sealed class Artifact(private val id: String, private val version: Version,
        private val feature: Feature? = null) : Info(id) {
      override fun invoke() = when (this) {
        is Normal -> ":$id"
        is Tagged -> ".${id.run { indexOf("-").let { if (it > 0) removeRange(it, length) else this } }}:$id"
      } + feature.orEmpty() + version()

      fun featureId() = feature?.id() ?: ""

      fun feature(id: String, version: String = ""): Artifact {
        val artifact = this.id + feature.orEmpty()
        val versionId = if (version.isEmpty()) this.version.id() else version
        return when (this) {
          is Normal -> Normal(artifact, versionId, id)
          is Tagged -> Tagged(artifact, versionId, id)
        }
      }

      class Normal internal constructor(id: String, version: String, feature: String = "") : Artifact(id,
          Version(version), if (feature.isEmpty()) null else Feature(feature))

      class Tagged internal constructor(id: String, version: String, feature: String = "") : Artifact(id,
          Version(version), if (feature.isEmpty()) null else Feature(feature))
    }

    class Feature internal constructor(id: String) : Info(id) {
      override fun invoke() = "-${super.invoke()}"
    }

    class Version internal constructor(id: String) : Info(id) {
      override fun invoke() = ":${super.invoke()}"
    }
  }

  open class Grouping(groupId: String, artifacts: Grouping.() -> List<Artifact>) {
    private val dependencies: HashMap<String, Dependency> = hashMapOf()

    operator fun invoke(each: (Dependency) -> Unit) = dependencies.forEach { each(it.value) }
    operator fun invoke(key: String) = dependencies[key]!!

    init {
      apply {
        artifacts(this).forEach {
          dependencies[it.featureId().run { if (isEmpty()) it.id() else this }] = Dependency(Group(groupId), it)
        }
      }
    }

    fun normal(id: String, version: String, feature: String = "") = Normal(id, version, feature)
    fun tagged(id: String, version: String, feature: String = "") = Tagged(id, version, feature)
  }
}

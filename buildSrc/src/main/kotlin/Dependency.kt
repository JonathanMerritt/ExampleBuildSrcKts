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

    class Group internal constructor(id: String) : Info(id)

    sealed class Artifact(private val id: String, private val version: Version,
        private val feature: Feature = Feature("")) : Info(id) {
      override fun invoke() = when (this) {
        is Normal -> ":$id"
        is Tagged -> ".${id.run { indexOf("-").let { if (it > 0) removeRange(it, length) else this } }}:$id"
      } + feature() + version()

      fun tag() = feature.id().run { if (isEmpty()) id else this }

      operator fun invoke(id: String, version: String = this.version.id()) =
          when (this) {
            is Normal -> Normal(this.id + this.feature(), version, id)
            is Tagged -> Tagged(this.id + this.feature(), version, id)
          }

      class Normal internal constructor(id: String, version: String, feature: String = "") : Artifact(id,
          Version(version), Feature(feature))

      class Tagged internal constructor(id: String, version: String, feature: String = "") : Artifact(id,
          Version(version), Feature(feature))
    }

    class Feature internal constructor(id: String) : Info(id) {
      override fun invoke() = super.invoke().run { if (isEmpty()) "" else "-$this" }
    }

    class Version internal constructor(id: String) : Info(id) {
      override fun invoke() = super.invoke().run { if (isEmpty()) "" else ":$this" }
    }
  }

  open class Grouping(groupId: String, artifacts: Grouping.() -> List<Artifact>) {
    private val dependencies: HashMap<String, Dependency> = hashMapOf()

    operator fun invoke(each: (Dependency) -> Unit) = dependencies.forEach { each(it.value) }
    operator fun invoke(key: String) = dependencies[key]!!

    init {
      apply { artifacts(this).forEach { dependencies[it.tag()] = Dependency(Group(groupId), it) } }
    }

    fun normal(id: String, version: String, feature: String = "") = Normal(id, version, feature)
    fun tagged(id: String, version: String, feature: String = "") = Tagged(id, version, feature)
  }
}

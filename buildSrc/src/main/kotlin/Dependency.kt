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
data class Dependency(private val group: Group, private val artifact: Artifact? = group.artifact) : ToString {
  override fun invoke() = "${group()}${artifact.orEmpty()}"

  sealed class Info(private val id: String) : ToString {
    override fun invoke() = id

    open class Group(id: String, val artifact: Artifact? = null) : Info(id) {
      fun add(artifact: Artifact? = this.artifact) = Dependency(this, artifact)
      fun addNormal(id: String, versionId: String) = add(Normal(id, versionId))
      fun addTagged(id: String, versionId: String) = add(Tagged(id, versionId))
      fun addFeature(id: String): Dependency = add(artifact!!.feature(id))
    }

    sealed class Artifact(private val id: String, private val versionId: String = "",
        private val version: Version = Version(versionId), private val feature: Feature? = null) : Info(id) {
      override fun invoke() = when (this) {
        is Normal -> ":$id"
        is Tagged -> ".${id.trimFeatures()}:$id"
      } + feature.orEmpty() + version()


      fun feature(id: String, versionId: String = this.versionId) = Feature(id).let {
        when (this) {
          is Normal -> Normal(this.id + feature.orEmpty(), versionId, it)
          is Tagged -> Tagged(this.id + feature.orEmpty(), versionId, it)
        }
      }

      open class Normal(id: String, versionId: String, feature: Feature? = null) : Artifact(id, versionId,
          feature = feature)

      open class Tagged(id: String, versionId: String, feature: Feature? = null) : Artifact(id, versionId,
          feature = feature) {
        fun String.trimFeatures() = indexOf("-").let { if (it > 0) removeRange(it, length) else this }
      }
    }

    class Feature internal constructor(id: String) : Info(id) {
      override fun invoke() = "-${super.invoke()}"
    }

    class Version internal constructor(id: String) : Info(id) {
      override fun invoke() = ":${super.invoke()}"
    }
  }
}

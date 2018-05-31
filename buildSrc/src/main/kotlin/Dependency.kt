import Dependency.Info.Artifact
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
  operator fun invoke() = group.pathId() + artifact.pathId() + artifact.version.pathId()
  operator fun invoke(artifact: Artifact) = group(artifact)
  operator fun invoke(artifactId: String) = this(artifact(artifactId))

  sealed class Info(private val id: String) {
    open fun pathId() = ":$id"

    open class Group(private val id: String, private val artifact: Info.Artifact? = null) : Info(id) {
      override fun pathId() = id

      operator fun invoke(artifact: Info.Artifact) = Dependency(this, artifact)
      operator fun invoke(artifactId: String) = this(artifact!!(artifactId))
      operator fun invoke() = this(artifact!!)

      open class Artifact(private val id: String, version: Version) : Info.Artifact(id, version) {
        override fun pathId() = ".$id" + super.pathId()
      }
    }

    open class Artifact(id: String, val version: Version) : Info(id) {
      operator fun invoke(id: String, version: Version = this.version) = object : Artifact(id, version) {
        override fun pathId() = this@Artifact.pathId() + "-$id"
      }
    }

    open class Version(id: String) : Info(id)
  }
}

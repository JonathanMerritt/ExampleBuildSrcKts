import Dependency.Info.Artifact
import Dependency.Info.Artifact.Type.FEATURE
import Dependency.Info.Artifact.Type.GROUP
import Dependency.Info.Artifact.Type.NORMAL
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
  operator fun invoke(artifact: Artifact) = group(artifact)
  operator fun invoke() = group.path() + artifact.path() + artifact.version.path()

  sealed class Info(private val id: String) {
    open fun path() = when (this) {
      is Group -> id
      else -> ":$id"
    }

    open class Group(id: String, private val artifact: Info.Artifact? = null) : Info(id) {
      operator fun invoke(artifact: Info.Artifact) = Dependency(this, artifact)
      operator fun invoke(artifactId: String) = this(artifact!!(artifactId))
      operator fun invoke() = this(artifact!!)
    }

    open class Artifact(private val id: String, val version: Version, private val type: Type = NORMAL) : Info(id) {
      enum class Type { NORMAL, GROUP, FEATURE }

      override fun path() = when (type) {
        GROUP -> ".$id" + super.path()
        FEATURE -> id
        else -> super.path()
      }

      operator fun invoke(id: String, version: Version = this.version) = Artifact(path() + "-$id", version,
          FEATURE)
    }

    class Version(id: String) : Info(id)
  }
}

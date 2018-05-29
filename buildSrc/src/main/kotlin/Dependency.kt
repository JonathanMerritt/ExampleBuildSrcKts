import Dependency.Info.Artifact
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
    group: Group = Group("group"),
    artifact: Artifact = Artifact("artifact"),
    version: Version = Version("version")
) {

  val id = group() + artifact() + version()

  sealed class Info(val id: String) {
    open operator fun invoke() = id
    open class Group(id: String) : Info(id) {

      open class Artifact(id: String) : Info.Artifact(id) {
        override fun invoke() = ".$id" + super.invoke()
      }
    }

    open class Artifact(id: String) : Info(id) {
      override fun invoke() = ":$id"

      operator fun invoke(id: String) = object : Artifact(id) {
        override fun invoke() = this@Artifact() + "-$id"
      }
    }

    open class Version(id: String) : Info(":$id")
  }
}

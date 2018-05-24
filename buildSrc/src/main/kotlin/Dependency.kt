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

sealed class Dependency(open val group: String, open val name: String, open val version: String) {
  open class Type1(override val group: String, override val name: String, override val version: String,
      val groupName: String = "$group.$name") : Dependency(group, name, version)

  open class Type2(override val group: String, override val name: String,
      override val version: String) : Dependency(group, name, version)

  operator fun invoke() = "${when (this) {is Type1 -> groupName
    else -> group
  }}:$name:$version"
}

## *ExampleBuildSrcKts*

**What** - Example for using a kotlin-dsl buildSrc module and project wide `*.gradle.kts` files.

**Why** - When buildSrc uses the kotlin-dsl in a project that doesn't has it's downsides.
Limited use of the kotlin language features being the most prominent.

**Hows**
* Created android studio project with library and buildSrc modules.
* Renamed all gradle files to `*.gradle.kts`.
* Basic convrrsions *(recommend editing outside android studio for the initial changes)*.
* Begin...
---
**Kotlin-Dsl**

*issues*
* Major lag in errors and their clearing. *(multiple java.exes' etc...)*
* buildSrc changes require android studio restart to take effect *(very inconvenient)*.
* ...

**Project**

*issues*
* Dependency types arnt great.
* Using objects to store values may be overkill.
* ...

*hopes*
* ~~Feature needs to beable to combine with other features~~.
* Add android setup.
* See about plugins *(bintray/maven/custom)*
* Try creating tasks and/or plugins to auto write versions, based on data(?) from the maven repo.
* Figure out a releasing configuration.
* ...

## [License][LICENSE]
    Copyright 2018 Jonathan Merritt 11R00TT00R11@GMAIL.COM

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[LICENSE]: https://github.com/JonathanMerritt/ExampleBuildSrcKts/blob/master/LICENSE.txt

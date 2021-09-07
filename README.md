<h1 align="center">Lottiefiles App</h1><br>
<p align="center">  
Lottiefiles App is an Android application built using jetpack compose and Hilt, based on modern Android technology stacks and the MVI architecture. 
The application is a clone of the [Lottiefiles](https://lottiefiles.com/mobile) mobile application and allows the user to retrieve and save animations, animators and articles locally from an API rest
</p>
<br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/eric-ampire/android-study-case/actions"><img alt="Build Status" src="https://github.com/eric-ampire/lottiefiles-app/workflows/Android%20CI/badge.svg"/></a>
</p>

## Download
Go to the [Releases](https://github.com/eric-ampire/lottiefiles-app/releases) to download the latest APK.

## Screenshots
<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/93901108-238eb000-fd31-11ea-9fac-c9ba1eca146c.gif" width="32%"/>
<img src="/preview/preview0.gif" width="32%"/>
<img src="/preview/preview1.gif" width="32%"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt (alpha) for dependency injection.
- JetPack
    - Compose - A modern toolkit for building native Android UI.
    - Coroutines Flow - notify domain layer data to views.
    - Lifecycle - dispose observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
    - Room Persistence - construct database.
    - App Startup - Provides a straightforward, performant way to initialize components at application startup.
- Architecture
    - MVI Architecture (Declarative View - Intent - Model)
    - Clear Architecture pattern
- Material Design & Animations
- [Lottie Android](https://airbnb.design/lottie/) - Lottie is a library that renders After Effects animations in real time, allowing apps to use animations as easily as they use static images.
- [Accompanist](https://github.com/google/accompanist) - A collection of extension libraries for Jetpack Compose.
- [Maverics](https://airbnb.io/mavericks/#/) - Mavericks is an Android MVI framework that is both easy to learn yet powerful enough for the most complex flows
- [Ktor](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Timber](https://github.com/JakeWharton/timber) - Logging.

## Challenge and difficulties encountered
* The API was not complete and the stories and animators returned by the API did not have IDs, I had to find a strategy to assign IDs to data that did not have them to avoid duplication in the database. 
* Animations didn't come with any information about whether an animation was popular, recent or featured, so I had to add an extra field to make the queries properly.

# License

```
Designed and developed by 2020 ericampire (Eric Ampire)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

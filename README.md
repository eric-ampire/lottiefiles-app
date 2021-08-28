<h1 align="center">Android Study Case</h1></br>
<p align="center">  
A demo Android Study Case app using compose and Hilt based on modern Android tech-stacks and MVI architecture. Fetching data from the network and integrating persisted data in the database via repository pattern using compose.
</p>
</br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/eric-ampire/android-study-case/actions"><img alt="Build Status" src="https://github.com/eric-ampire/android-study-case/workflows/Android%20CI/badge.svg"/></a>
  <a href="https://proandroiddev.com/exploring-jetpack-compose-with-dagger-hilt-and-viewmodels-3e0ca939daa7"><img alt="Medium" src="https://eric-ampire.github.io/badges/Story-Medium.svg"/></a>
  <a href="https://github.com/eric-ampire"><img alt="Profile" src="https://eric-ampire.github.io/badges/skydoves.svg"/></a> 
</p>

## Download
Go to the [Releases](https://github.com/eric-ampire/android-study-case/releases) to download the latest APK.

## Screenshots
<p align="center">
<img src="#" width="32%"/>
<img src="#" width="32%"/>
<img src="#" width="32%"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Hilt (alpha) for dependency injection.
- JetPack
    - Compose - A modern toolkit for building native Android UI.
    - LiveData - notify domain layer data to views.
    - Lifecycle - dispose observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
    - Room Persistence - construct database.
    - App Startup - Provides a straightforward, performant way to initialize components at application startup.
- Architecture
    - MVI Architecture (Declarative View - Intent - Model)
    - Clear Architecture pattern
- Material Design & Animations
- [Accompanist](https://github.com/google/accompanist) - A collection of extension libraries for Jetpack Compose.
- [Orbit-MVI](https://github.com/orbit-mvi/orbit-mvi) - Orbit is a Redux/MVI-like library - but without the baggage. It's so simple we think of it as MVVM+.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Timber](https://github.com/JakeWharton/timber) - logging.

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/eric-ampire/android-study-case/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/eric-ampire)__ me for my next creations! 🤩

# License
```xml
Designed and developed by 2021 eric-ampire (Eric Ampire)


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

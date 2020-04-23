# GetYourGuide take-home test

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

The project was developed and tested on an old Android phone so there were some warnings about platform and versions.
I included some libraries to get rid of these warnings.

The project is to display the list of cameras of Singapore Traffic to Google Map.
  - User can view the list of markers on the Google Map. Each marker is a camera.
  - User can navigate by moving around or zooming the map
  - User can tap on 1 marker to open a popup which displays the latest image taken by the camera
  - The images is updated according to the source. Currently on its document, images are updated every 1 minute. The app also update its source every 1 minutes
# Tech details
  - Architecture: **MVVM** for demonstrating purpose. MVP can be used because it is less complex.
  - **Databinding** is used in 1 Fragment
  - DI: **Koin**, most of the ViewModels, Fragments, FragmentFactories, Repositoiries are code in a way that they can be injected and obtained by Koin at anytime
  - Network call: **Retrofit** with OkHttp3 and a custom adapter. **Coroutines** with suspend function can be used instead
  - **Rx** is also used for observing parameter, replacing callbacks
  - **LiveData** is used to communicate between ViewModel & UI
  - Actions are handled through **AppAction** LiveData. All actions are bound to a Enum value
# Testing
  - **Junit4**(4.12) is used.
  - **Espresso** is used to test some UI coponent.
  - **mockk** is used for mocking object, functions...
  - Testing forcus on ViewModels, Repositories, Fragments and Activities

### Installation

The project should be installed directly on emulator or Android devices without issues

### Todos
 - Improve UIs
 - Add Night Mode
 - Local database can be used because cameras are rarely changed. App can store a list of cameras to display immediately when user opens the app. Later, it can update the source. User then can use the app offline.

License
----

MIT

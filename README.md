<p align="center">
   <a href="https://zenbreak.app">
      <img src="https://raw.githubusercontent.com/Giuliopime/ZenBreak/main/assets/logo_macos.png" alt="ZenBreak" width="124">
   </a>
<p>
 
<h1 align="center">ZenBreak</h1>
<p align="center">
   <img alt="GitHub all releases" src="https://img.shields.io/github/downloads/Giuliopime/ZenBreak/total">
   <img alt="GitHub release (with filter)" src="https://img.shields.io/github/v/release/Giuliopime/ZenBreak">
   <img alt="homebrew cask" src="https://img.shields.io/homebrew/cask/v/zenbreak">
</p>


## Overview
<p align="center">
      <img src="https://raw.githubusercontent.com/Giuliopime/ZenBreak/main/assets/screenshot.png" alt="ZenBreak screenshot"
      width="50%"
      align="right">
</p>
<br>

This app simply reminds you to take a break from the screen, it has customisable behaviour and appereance to suit everyone needs.  
(*Check out the [website](https://zenbreak.app) for more screenshots*)  


This is a [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) app built for the [Jetbrains Contest](https://blog.jetbrains.com/kotlin/2022/10/join-the-kotlin-multiplatform-contest/) and for personal use!  
It currently supports MacOS, Windows and Linux, but I'll consider porting it to Android and iOS if I receive many requests for that.  

## Installing  
You can install the app via [zenbreak.app](https://zenbreak.app) or via [GitHub releases](https://github.com/Giuliopime/ZenBreak/releases).  

If you are on mac you can use Brew too:
```sh
brew install --cask zenbreak
``` 

## Inspiration and resources  
This project has been insipired by [BreakTimer](https://breaktimer.app).  
I didn't want a js runtime just for a simple menu bar app so I took the opportunity to build ZenBreak ^^  

I've found a few projects that really helped learning the technologies used in this project:
- [John O'Reilly](https://johnoreilly.dev/)  
thank you so much for all the open source repos of your GitHub and the blog, helped a ton expecially with macOS related stuff!
- [TomatoBar](https://github.com/ivoronin/TomatoBar)  
great open source macOS menu bar app, gave me a solid base for the macOS app!
- [Conveyor](https://conveyor.hydraulic.dev/)  
amazing tool to built desktop installers for your app, recommend 💯
- [Kotlin Community](https://kotlinlang.org/community/) of course :>


## Contributing
Feel free to contribute to the project!  
You can take a look at the [issues](https://github.com/Giuliopime/ZenBreak/issues) to see what's currently missing or needs improvement.  

Checkout the following section to learn how the project is structured.

## Development
#### Set up the environment

> **Warning**  
> You need a Mac with macOS to write and run iOS-specific code on simulated or real devices.  
> This is an Apple requirement.

To work with this template, you need the following:

* A machine running a recent version of macOS
* [Xcode](https://apps.apple.com/us/app/xcode/id497799835)
* [Android Studio](https://developer.android.com/studio)
* The [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile)
* The [CocoaPods dependency manager](https://kotlinlang.org/docs/native-cocoapods.html)

### Check your environment

Before you start, use the [KDoctor](https://github.com/Kotlin/kdoctor) tool to ensure that your development environment  
is configured correctly:

1. Install KDoctor with [Homebrew](https://brew.sh/):
   ```sh  
    brew install kdoctor  
   ```  
2. Run KDoctor in your terminal:
   ```sh  
   kdoctor  
   ```  
   If everything is set up correctly, you'll see valid output:
    ```text  
    Environment diagnose (to see all details, use -v option):  
    [✓] Operation System  
    [✓] Java  
    [✓] Android Studio  
    [✓] Xcode  
    [✓] Cocoapods  
      
    Conclusion:  
     ✓ Your system is ready for Kotlin Multiplatform Mobile development!  
    ```
   Otherwise, KDoctor will highlight which parts of your setup still need to be configured and will suggest a way to fix them.

#### Examine the project structure

Open the project in Android Studio and switch the view from **Android** to **Project** to see all the files and targets belonging to the project:

This Compose Multiplatform project includes 4 modules:
- `shared-core`
- `shared-compose-core`
- `shared-compose-popup`
- `shared-compose-settings`
- `androidApp`
- `desktopApp`
- `multiplatformApp`
- `zenbreak.app`

Each module contains a README file, refer to those for informations about each module.  
All plugins and dependencies are handled via the version catalog, which you can find in `gradle/libs.versions.toml`.

#### Run the application

##### Running on macOS
Open the `multiplatformApp` folder with XCode and run the project from XCode directly.

##### On desktop
To run the desktop application in Android Studio, select `desktopApp` in the list of run configurations and click **Run**:  
You can also run Gradle tasks in the terminal:
- `./gradlew run` to run application
- `./gradlew package` to store native distribution into `build/compose/binaries`

##### On Android
To run the application on an Android emulator:
1. Ensure you have an Android virtual device available.  
   Otherwise, [create one](https://developer.android.com/studio/run/managing-avds#createavd)
2. In the list of run configurations, select `androidApp`.
3. Choose your virtual device and click **Run**:

To install the Android application on a real Android device or an emulator, run `./gradlew installDebug` in the terminal.
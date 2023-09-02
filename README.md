# ZenBreak

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
- `shared-compose`
- `androidApp`
- `desktopApp`
- `multiplatformApp`

Each module contains a README file, refer to those for informations about each module.  
All plugins and dependencies are handled via the version catalog, which you can find in `gradle/libs.versions.toml`.

#### Run the application
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

##### On iOS and macOS

###### Running on macOS
Open the `multiplatformApp` folder with XCode and run the project from XCode directly.
###### Running on a simulator
To run the application on an iOS simulator in Android Studio, modify the `iosApp` run configuration:
1. In the list of run configurations, select **Edit Configurations**
2. Navigate to **iOS Application** | **iosApp**
3. In the **Execution target** list, select your target device. Click **OK**
4. The `iosApp` run configuration is now available. Click **Run** next to your virtual device:

###### Running on a real iOS device
You can run the application on a real iOS device for free.  
To do so, you'll need the following:
- The `TEAM_ID` associated with your [Apple ID](https://support.apple.com/en-us/HT204316)
- The iOS device registered in Xcode

> **Note**  
> Before you continue, we suggest creating a simple "Hello, world!" project in Xcode to ensure you can successfully run apps on your device.  
> You can follow the instructions below or watch this [Stanford CS193P lecture recording](https://youtu.be/bqu6BquVi2M?start=716&end=1399).

###### Finding your Team ID
In the terminal, run `kdoctor --team-ids` to find your Team ID.  
KDoctor will list all Team IDs currently configured on your system, for example:
```text  
3ABC246XYZ (Max Sample)  
ZABCW6SXYZ (SampleTech Inc.)  
```  
###### Alternative way to find your Team ID
If KDoctor doesn't work for you, try this alternative method:
1. In Android Studio, run the `iosApp` configuration with the selected real device. The build should fail
2. Open the `multiplatformApp` folder with XCode
3. In the left-hand menu, select `multiplatformApp`
4. Navigate to **Signing & Capabilities**.
5. In the **Team** list, select your team.

If you haven't set up your team yet, use the **Add account** option and follow the steps.  
To run the application, set the `TEAM_ID`:
1. Navigate to the `multiplatformApp/Configuration/Config.xcconfig` file.
2. Set your `TEAM_ID`.
3. Re-open the project in Android Studio. It should show the registered iOS device in the `iosApp` run configuration.  
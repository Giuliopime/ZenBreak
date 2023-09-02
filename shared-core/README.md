# shared-core
This module contains the shared logic of the multiplatform app

## Development
To use this module in iOS and macOS targets, build an xcFramework with the cocoapods plugin:  
```shell
./gradlew shared-core:podPublishXCFramework
```  

You can then add the XCFramework to the apple multiplatform project:
1. Open `./multiplatformApp` with XCode
2. In the left-hand menu, select `multiplatformApp`
3. Scroll down to `Frameworks, Libraries, and Embedded Content`
4. Click on the plus icon
5. Click `Add other...` and `Add files...`
6. Open `shared-core/build/cocoapods/publish/release/SharedCore.xcFramework`
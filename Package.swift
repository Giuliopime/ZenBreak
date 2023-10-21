// swift-tools-version:5.3
import PackageDescription

let packageName = "ZenBreakCoreKit"

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13),
        .macOS(.v10_15)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            path: "./shared-core/build/XCFrameworks/debug/\(packageName).xcframework"
        )
        ,
    ]
)
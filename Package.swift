// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/Giuliopime/ZenBreak/dev/giuliopime/zenbreakcorekit/shared-core-kmmbridge/0.1.1/shared-core-kmmbridge-0.1.1.zip"
let remoteKotlinChecksum = "2f60211aceaba5d94616d420b5e6a59ea013bf91ec8255142f64f057753a7647"
let packageName = "ZenBreakCoreKit"
// END KMMBRIDGE BLOCK

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13)
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
            url: remoteKotlinUrl,
            checksum: remoteKotlinChecksum
        )
        ,
    ]
)
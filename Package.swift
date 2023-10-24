// swift-tools-version:5.3
import PackageDescription

// BEGIN KMMBRIDGE VARIABLES BLOCK (do not edit)
let remoteKotlinUrl = "https://maven.pkg.github.com/Giuliopime/ZenBreak/dev/giuliopime/zenbreakcorekit/shared-core-kmmbridge/0.1.0/shared-core-kmmbridge-0.1.0.zip"
let remoteKotlinChecksum = "8509b2edc1d45eb026052a02864d27aef0e63ca419c275eb501ac31e25a60470"
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
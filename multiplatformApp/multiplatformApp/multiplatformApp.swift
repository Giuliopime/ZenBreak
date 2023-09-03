//
//  multiplatformAppApp.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 12/07/23.
//

import SwiftUI
import sharedCore

@main
struct multiplatformApp: App {
    
    init() {
        KoinKt.doInitKoin { _ in }
    }
    
    var body: some Scene {
        #if os(macOS)
        MenuBarExtra("ZenBreak", systemImage: "tree.fill") {
            MenuBarWindow()
        }.menuBarExtraStyle(.window)
        #endif
    }
}

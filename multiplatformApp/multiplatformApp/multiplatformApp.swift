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
    @NSApplicationDelegateAdaptor(ZbStatusItem.self) var appDelegate
    
    init() {
        ZbStatusItem.shared = appDelegate
        
        KoinKt.doInitKoin { _ in }
    }
    
    var body: some Scene {
        #if os(macOS)
        MenuBarExtra("ZenBreak", systemImage: "tree.fill") {
            ZbPopoverView()
        }.menuBarExtraStyle(.window)
        #endif
    }
}

class ZbStatusItem: NSObject, NSApplicationDelegate {
    private var popover = NSPopover()
    private var statusBarItem: NSStatusItem?
    static var shared: ZbStatusItem!

    func applicationDidFinishLaunching(_: Notification) {
        NSApp.activate(ignoringOtherApps: true)
    }
}

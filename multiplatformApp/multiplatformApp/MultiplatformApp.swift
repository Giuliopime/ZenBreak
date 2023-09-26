//
//  multiplatformAppApp.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 12/07/23.
//

import SwiftUI
import sharedCore
import sharedComposePopup

@main
struct MultiplatformApp: App {
    @NSApplicationDelegateAdaptor(ZbAppDelegate.self) var appDelegate
    
    init() {
        // Init dependency injection
        KoinKt.doInitKoin { _ in }
        
        ZbAppDelegate.shared = appDelegate
    }
    
    var body: some Scene {
        #if os(macOS)
        MenuBarExtra("ZenBreak", systemImage: "tree.fill") {
            ZbPopoverView()
        }.menuBarExtraStyle(.window)
        #endif
    }
}

class ZbAppDelegate: NSObject, NSApplicationDelegate {
    private var popover = NSPopover()
    private var breakWindowController = ZbBreakWindowController()
    private var statusBarItem: NSStatusItem?
    static var shared: ZbAppDelegate!

    func applicationDidFinishLaunching(_: Notification) {
        NSApp.activate(ignoringOtherApps: true)
        NSApp.presentationOptions = [.hideDock, .hideMenuBar]
    }
    
    func showBreakWindow() {
        breakWindowController.window?.makeKeyAndOrderFront(nil)
        // breakWindowController.showWindow(nil)
    }
}

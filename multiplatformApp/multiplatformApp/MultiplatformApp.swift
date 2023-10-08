//
//  multiplatformAppApp.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 12/07/23.
//

import SwiftUI
import sharedCore

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
            ZbPopoverView(viewModel: ZbAppDelegate.shared.viewModel)
        }.menuBarExtraStyle(.window)
        #endif
    }
}

class ZbAppDelegate: NSObject, NSApplicationDelegate {
    static var shared: ZbAppDelegate!
    
    private var popover = NSPopover()
    private var breakWindowController: ZbBreakWindowController?
    private var statusBarItem: NSStatusItem?
    
    @StateObject var viewModel = ZbViewModel()

    func applicationDidFinishLaunching(_: Notification) {
        breakWindowController = ZbBreakWindowController(viewModel: viewModel)
    }
    
    func showBreakWindow() {
        breakWindowController?.showWindow(self)
    }
    
    func hideBreakWindow() {
        breakWindowController?.window?.close()
    }
}

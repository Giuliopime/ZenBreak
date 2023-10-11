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
    @StateObject var viewModel = ZbViewModel.shared
    
    init() {
        // Init dependency injection
        KoinKt.doInitKoin { _ in }
        
        ZbAppDelegate.shared = appDelegate
    }
    
    var body: some Scene {
        #if os(macOS)
        MenuBarExtra("ZenBreak", systemImage: "tree.fill") {
            ZbPopoverView(viewModel: viewModel)
        }.menuBarExtraStyle(.window)
        #endif
    }
}

class ZbAppDelegate: NSObject, NSApplicationDelegate {
    static var shared: ZbAppDelegate!
    
    private var popover = NSPopover()
    private var breakWindowController: ZbBreakWindowController?
    private var statusBarItem: NSStatusItem?

    func applicationDidFinishLaunching(_: Notification) {
        breakWindowController = ZbBreakWindowController()
    }
    
    func showBreakWindow() {
        breakWindowController?.show(viewModel: ZbViewModel.shared)
    }
    
    func hideBreakWindow() {
        breakWindowController?.hide()
    }
}

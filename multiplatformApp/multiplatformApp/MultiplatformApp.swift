//
//  multiplatformAppApp.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 12/07/23.
//

import SwiftUI
import sharedCore
import FluidMenuBarExtra

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
        Settings {
            EmptyView()
        }
        
        /*
         This has weird resizing animations
        MenuBarExtra("ZenBreak", systemImage: "tree.fill") {
            ZbPopoverView(viewModel: viewModel)
        }.menuBarExtraStyle(.window)
         */
        #endif
    }
}

class ZbAppDelegate: NSObject, NSApplicationDelegate {
    static var shared: ZbAppDelegate!
    
    private var menuBarExtra: FluidMenuBarExtra?
    private var breakWindowController: ZbBreakWindowController?

    func applicationDidFinishLaunching(_: Notification) {
        self.menuBarExtra = FluidMenuBarExtra(title: "ZenBreak", systemImage: "cloud.fill") {
            ZbPopoverView(viewModel: ZbViewModel.shared)
                .frame(width: 300)
        }
        
        breakWindowController = ZbBreakWindowController()
    }
    
    func showBreakWindow() {
        breakWindowController?.show(viewModel: ZbViewModel.shared)
    }
    
    func hideBreakWindow() {
        breakWindowController?.hide()
    }
}

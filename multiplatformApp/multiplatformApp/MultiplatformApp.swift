//
//  multiplatformAppApp.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 12/07/23.
//

import SwiftUI
import ZenBreakCoreKit
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
    
    private let aboutOptions: [NSApplication.AboutPanelOptionKey : Any] = [
        NSApplication.AboutPanelOptionKey.credits: NSAttributedString(
            string: "Privacy Policy: https://zenbreak.app/privacy\n\nTerms of Use: https://zenbreak.app/terms",
            attributes: [
                NSAttributedString.Key.font: NSFont.systemFont(
                    ofSize: 10)
            ]
        )
    ]
    
    private var menuBarExtra: FluidMenuBarExtra?
    private var breakWindowController: ZbBreakWindowController?
    // another logo idea meh - private let image = "circle.circle.fill" // "swirl.circle.righthalf.filled"
    private let image = "swirl.circle.righthalf.filled"

    func applicationDidFinishLaunching(_: Notification) {
        self.menuBarExtra = FluidMenuBarExtra(title: "ZenBreak", systemImage: image) {
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
    
    func showAboutDialog() {
        NSApp.activate(ignoringOtherApps: true)
        NSApp.orderFrontStandardAboutPanel(options: aboutOptions)
    }
}

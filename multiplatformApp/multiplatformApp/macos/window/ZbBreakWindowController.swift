//
//  ZbBreakWindowController.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 26/09/23.
//

import Foundation
import AppKit
import SwiftUI

class ZbBreakWindowController: NSWindowController {
    
    convenience init() {
        
        guard let screenFrame = NSScreen.main?.frame else {
            fatalError("Failed to obtain the main screen's frame")
        }
        
        let window = ZbBreakWindow(
            contentRect: screenFrame,
            styleMask: [.borderless],
            backing: .buffered,
            defer: false
        )
        
        window.collectionBehavior = [.canJoinAllSpaces]
        
        window.titlebarAppearsTransparent = true
        window.isOpaque = false
        
        /*
        let darkMode = UserDefaults.standard.string(forKey: "AppleInterfaceStyle") != nil
        
        var bgColor = darkMode ? NSColor(red: 0.07, green: 0.07, blue: 0.07, alpha: 0.6) : NSColor(red: 0.9, green: 0.9, blue: 0.9, alpha: 0.8)
        */
        
        window.backgroundColor = NSColor.clear
        
        window.level = .floating
        window.center()
        
        self.init(window: window)
    }
    
    func show(viewModel: ZbViewModel) {
        window?.contentView = NSHostingView(rootView: ZbBreakWindowView(viewModel: viewModel))
        showWindow(nil)
    }
    
    func hide() {
        window?.close()
        window?.contentViewController?.view.removeFromSuperview()
    }
}

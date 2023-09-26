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
        
        window.contentView = NSHostingView(rootView: ZbBreakWindowView())
        
        window.level = .floating
        window.center()
        
        self.init(window: window)
    }
}

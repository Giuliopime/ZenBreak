//
//  AppereanceTabView.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 03/09/23.
//

import SwiftUI
import sharedCore
import DynamicColor

enum NotificationType {
    case popup, notification
}

struct AppereanceTabView: View {
    @Environment(\.scenePhase) private var scenePhase
    @ObservedObject var viewModel: ZbViewModel
    
    private var notificationType: Binding<NotificationType> { Binding(
        get: {
            viewModel.settings.popupNotification ? NotificationType.popup : NotificationType.notification
        },
        set: { popup in
            viewModel.setPopupNotification(popupNotification: popup == NotificationType.popup ? true : false)
        }
    )}
    
    private var primary: Binding<Color> { Binding(
        get: {
            Color(hexString: viewModel.settings.primaryColor)
        },
        set: { color in
            var hex = ""
            #if os(macOS)
            hex = NSColor(color).toHexString()
            #elseif os(iOS)
            hex = UIColor(color).toHexString()
            #endif
            
            viewModel.setPrimaryColor(primary: hex)
        }
    )}
    
    private var textColor: Binding<Color> { Binding(
        get: {
            Color(hexString: viewModel.settings.textColor)
        },
        set: { color in
            var hex = ""
            
            #if os(macOS)
            hex = NSColor(color).toHexString()
            #elseif os(iOS)
            hex = UIColor(color).toHexString()
            #endif
            
            viewModel.setTextColor(text: hex)
        }
    )}
    
    private var message: Binding<String> { Binding(
        get: {
            viewModel.settings.breakMessage
        },
        set: { message in
            viewModel.setBreakMessage(message: message)
        }
    )}
    
    var body: some View {
        VStack {
            HStack {
                Text("Notification type")
                Spacer()
                Picker("Notification type", selection: notificationType) {
                    Text("Popup")
                        .tag(NotificationType.popup)
                    
                    Text("Notification")
                        .tag(NotificationType.notification)
                    
                }
                .fixedSize()
                .labelsHidden()
            }
            
            if (notificationType.wrappedValue == NotificationType.popup) {
                ColorPicker(selection: primary, supportsOpacity: false) {
                    Text("Primary color")
                        .frame(maxWidth: .infinity, alignment: .leading)
                }
                
                ColorPicker(selection: textColor, supportsOpacity: false) {
                    Text("Text color")
                        .frame(maxWidth: .infinity, alignment: .leading)
                }
            }
            
            TextField("Message", text: message, axis: .vertical)
                .lineLimit(2...)
        }
        .padding(4)
        .onChange(of: scenePhase) { phase in
            NSApp.activate(ignoringOtherApps: true)
        }
    }
}

struct AppereanceTabView_Previews: PreviewProvider {
    static var previews: some View {
        AppereanceTabView(viewModel: ZbViewModel())
    }
}

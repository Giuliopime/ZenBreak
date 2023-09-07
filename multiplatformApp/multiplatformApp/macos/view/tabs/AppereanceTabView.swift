//
//  AppereanceTabView.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 03/09/23.
//

import SwiftUI
import sharedCore

private enum NotificationType {
    case popup, notification
}

struct AppereanceTabView: View {
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
            Color(hex: viewModel.settings.primaryColor) ?? Color.primary
        },
        set: { color in
            guard let hex = color.toHex() else {
                return
            }
            
            viewModel.setPrimaryColor(primary: hex)
        }
    )}
    
    private var text: Binding<Color> { Binding(
        get: {
            Color(hex: viewModel.settings.textColor) ?? Color.secondary
        },
        set: { color in
            guard let hex = color.toHex() else {
                return
            }
            
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
            
            ColorPicker(selection: primary, supportsOpacity: false) {
                Text("Primary color")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
            
            ColorPicker(selection: text, supportsOpacity: false) {
                Text("Text color")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
            
            TextField("Message", text: message, axis: .vertical)
                .lineLimit(2...)
        }
        .padding(4)
    }
}

struct AppereanceTabView_Previews: PreviewProvider {
    static var previews: some View {
        AppereanceTabView(viewModel: ZbViewModel(repository: DefaultSettingsRepository()))
    }
}

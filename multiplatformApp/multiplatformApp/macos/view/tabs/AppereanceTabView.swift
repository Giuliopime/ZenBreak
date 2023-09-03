//
//  AppereanceTabView.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 03/09/23.
//

import SwiftUI

private enum NotificationType {
    case popup, notification
}

struct AppereanceTabView: View {
    @State private var notificationType = NotificationType.popup
    @State private var primary = Color.blue
    @State private var text = Color.white
    @State private var message = "Take a break"
    
    var body: some View {
        VStack {
            HStack {
                Text("Notification type")
                Spacer()
                Picker("Notification type", selection: $notificationType) {
                    Text("Popup")
                        .tag(NotificationType.popup)
                    
                    Text("Notification")
                        .tag(NotificationType.notification)

                }
                .fixedSize()
                .labelsHidden()
            }
            
            ColorPicker(selection: $primary) {
                Text("Primary color")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
            
            ColorPicker(selection: $text) {
                Text("Text color")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
            
            TextField("Message", text: $message, axis: .vertical)
                .lineLimit(2...)
        }
        .padding(4)
    }
}

struct AppereanceTabView_Previews: PreviewProvider {
    static var previews: some View {
        AppereanceTabView()
    }
}

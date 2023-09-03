//
//  MenuBarWindow.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 03/09/23.
//

import SwiftUI

private enum ChildTabView {
    case behaviour, appereance, system
}

struct MenuBarWindow: View {
    @State private var activeTabView = ChildTabView.behaviour
    
    var body: some View {
        VStack(spacing: 8) {
            Button {

            } label: {
                Text("Enable")
                    .font(.system(.body).monospacedDigit())
                    .frame(maxWidth: .infinity)
            }
            .controlSize(.large)
            .keyboardShortcut(.defaultAction)
            
            Picker("", selection: $activeTabView) {
                Text("Behaviour").tag(ChildTabView.behaviour)
                Text("Appereance").tag(ChildTabView.appereance)
                Text("System").tag(ChildTabView.system)
            }
            .labelsHidden()
            .frame(maxWidth: .infinity)
            .pickerStyle(.segmented)
            
            GroupBox {
                switch activeTabView {
                case .behaviour:
                    BehaviourTabView()
                case .appereance:
                    AppereanceTabView()
                case .system:
                    SystemTabView()
                }
            }
            
            Group {
                Button {
                    NSApp.orderFrontStandardAboutPanel(self)
                } label: {
                    Text("About")
                    Spacer()
                    Text("⌘ A")
                        .foregroundColor(Color.gray)
                }
                .buttonStyle(.plain)
                .keyboardShortcut("a")
                
                Button {
                    NSApplication.shared.terminate(self)
                } label: {
                    Text("Quit")
                    Spacer()
                    Text("⌘ Q").foregroundColor(Color.gray)
                }
                .buttonStyle(.plain)
                .keyboardShortcut("q")
            }
        }.padding(12)
    }
}

struct MenuBarWindow_Previews: PreviewProvider {
    static var previews: some View {
        MenuBarWindow()
    }
}

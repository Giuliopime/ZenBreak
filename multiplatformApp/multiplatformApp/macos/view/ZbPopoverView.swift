//
//  MenuBarWindow.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 03/09/23.
//

import SwiftUI
import sharedCore

private enum ChildTabView {
    case behaviour, appereance, system
}

struct ZbPopoverView: View {
    @ObservedObject var viewModel: ZbViewModel
    
    @State private var activeTabView = ChildTabView.behaviour
    private var enabled: Binding<Bool> { Binding(
        get: {
            viewModel.settings.enabled
        },
        set: { enabled in
            viewModel.setEnabled(enabled: enabled)
        }
    )}

    
    var body: some View {
        VStack(spacing: 8) {
            Toggle(isOn: enabled) {
                Text("ZenBreak - \(enabled.wrappedValue ? "enabled" : "disabled")")
                    .font(.title3)
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
            .toggleStyle(.switch)
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
                    BehaviourTabView(viewModel: viewModel)
                case .appereance:
                    AppereanceTabView(viewModel: viewModel)
                case .system:
                    SystemTabView(viewModel: viewModel)
                }
            }
            
            Group {
                Button {
                    viewModel.startBreak()
                } label: {
                    Text("Start break now")
                    Spacer()
                    Text("⌘ S")
                        .foregroundColor(Color.gray)
                }
                .buttonStyle(.plain)
                .keyboardShortcut("s")
                
                Button {
                    viewModel.skipBreak()
                } label: {
                    Text("Reset break")
                    Spacer()
                    Text("⌘ R")
                        .foregroundColor(Color.gray)
                }
                .buttonStyle(.plain)
                .keyboardShortcut("r")
                
                Divider()
                
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
        ZbPopoverView(viewModel: ZbViewModel())
    }
}

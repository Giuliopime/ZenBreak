//
//  SystemTabView.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 03/09/23.
//

import SwiftUI
import sharedCore
import LaunchAtLogin

struct SystemTabView: View {
    @ObservedObject var viewModel: ZbViewModel
    
    private var reset: Binding<Bool> { Binding(
        get: {
            viewModel.settings.resetOnIdle
        },
        set: { reset in
            viewModel.setResetOnIdle(reset: reset)
        }
    )}
    
    private var startAtLogin: Binding<Bool> { Binding(
        get: {
            viewModel.settings.startAtLogin
        },
        set: { startAtLogin in
            viewModel.setStartAtLogin(start: startAtLogin)
        }
    )}
    
    var body: some View {
        VStack {
            Toggle(isOn: reset) {
                Text("Reset on idle")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
            .toggleStyle(.switch)
            .disabled(true)
            .help("Currently not supported")
            
            Toggle(isOn: startAtLogin) {
                Text("Start at login")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }.toggleStyle(.switch)
        }
        .padding(4)
    }
}

struct SystemTabView_Previews: PreviewProvider {
    static var previews: some View {
        SystemTabView(viewModel: ZbViewModel())
    }
}

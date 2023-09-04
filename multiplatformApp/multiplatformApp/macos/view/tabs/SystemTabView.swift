//
//  SystemTabView.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 03/09/23.
//

import SwiftUI
import sharedCore

struct SystemTabView: View {
    @ObservedObject var viewModel: ZbViewModel
    
    @State private var skipping = true
    @State private var snoozing = false
    
    var body: some View {
        VStack {
            Toggle(isOn: $skipping) {
                Text("Reset on idle")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }.toggleStyle(.switch)
            
            Toggle(isOn: $snoozing) {
                Text("Start at login")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }.toggleStyle(.switch)
        }
        .padding(4)
    }
}

struct SystemTabView_Previews: PreviewProvider {
    static var previews: some View {
        SystemTabView(viewModel: ZbViewModel(repository: DefaultSettingsRepository()))
    }
}

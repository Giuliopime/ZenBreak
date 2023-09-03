//
//  BehaviourTabView.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 03/09/23.
//

import SwiftUI

struct BehaviourTabView: View {
    @State private var frequency = 0
    @State private var skipping = true
    @State private var snoozing = false
    
    var body: some View {
        VStack {
            Stepper(value: $frequency, in: 1 ... 60) {
                Text("Break frequency")
                    .frame(maxWidth: .infinity, alignment: .leading)
                Text("20 min")
            }
            
            Stepper(value: $frequency, in: 1 ... 60) {
                Text("Break duration")
                    .frame(maxWidth: .infinity, alignment: .leading)
                Text("2 min")
            }
            
            Toggle(isOn: $skipping) {
                Text("Allow skipping")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }.toggleStyle(.switch)
            
            Toggle(isOn: $skipping) {
                Text("Allow snoozing")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }.toggleStyle(.switch)
        }
        .padding(4)
    }
}

struct BehaviourTabView_Previews: PreviewProvider {
    static var previews: some View {
        BehaviourTabView()
    }
}

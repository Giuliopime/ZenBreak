//
//  BehaviourTabView.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 03/09/23.
//

import SwiftUI
import sharedCore

struct BehaviourTabView: View {
    @ObservedObject var viewModel: ZbViewModel
    
    private var frequency: Binding<Int64> { Binding(
        get: {
            viewModel.settings.breakFrequency / 60000
        },
        set: { frequency in
            viewModel.setBreakFrequency(frequency: 1000)// frequency * 60000)
        }
    )}
    
    private var duration: Binding<Int64> { Binding(
        get: {
            viewModel.settings.breakDuration / 60000
        },
        set: { duration in
            viewModel.setBreakDuration(duration: duration * 60000)
        }
    )}
    
    private var snoozeDuration: Binding<Int64> { Binding(
        get: {
            viewModel.settings.breakSnoozeDuration / 60000
        },
        set: { snoozeDuration in
            viewModel.setBreakSnoozeDuration(snoozeDuration: snoozeDuration * 60000)
        }
    )}
    
    private var skipping: Binding<Bool> { Binding(
        get: {
            viewModel.settings.breakSkip
        },
        set: { skip in
            viewModel.setBreakSkip(skip: skip)
        }
    )}
    
    private var snoozing: Binding<Bool> { Binding(
        get: {
            viewModel.settings.breakSnooze
        },
        set: { snooze in
            viewModel.setBreakSnooze(snooze: snooze)
        }
    )}
    
    var body: some View {
        VStack {
            Stepper(value: frequency, in: 1 ... 1440) {
                Text("Break frequency")
                    .frame(maxWidth: .infinity, alignment: .leading)
                Text("\(frequency.wrappedValue) min")
            }
            
            Stepper(value: duration, in: 1 ... 60) {
                Text("Break duration")
                    .frame(maxWidth: .infinity, alignment: .leading)
                Text("\(duration.wrappedValue) min")
            }
            
            Toggle(isOn: skipping) {
                Text("Allow skipping")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }.toggleStyle(.switch)
            
            Toggle(isOn: snoozing) {
                Text("Allow snoozing")
                    .frame(maxWidth: .infinity, alignment: .leading)
            }.toggleStyle(.switch)
            
            if (snoozing.wrappedValue) {
                Stepper(value: snoozeDuration, in: 1 ... 1440) {
                    Text("Snooze duration")
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("\(snoozeDuration.wrappedValue) min")
                }
            }
        }
        .padding(4)
    }
}

struct BehaviourTabView_Previews: PreviewProvider {
    static var previews: some View {
        BehaviourTabView(viewModel: ZbViewModel())
    }
}

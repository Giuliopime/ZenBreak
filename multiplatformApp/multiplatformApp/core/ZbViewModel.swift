//
//  SettingsViewModel.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 04/09/23.
//

import Foundation
import KMPNativeCoroutinesAsync
import sharedCore
import LaunchAtLogin

@MainActor
class ZbViewModel: ObservableObject {
    private let repository: ISettingsRepository
    
    @Published var settings: ZbSettings = ZbSettings.Companion.shared.default_
    
    init(repository: ISettingsRepository) {
        self.repository = repository
        
        Task {
            do {
                let sequence = repository.getSettingsFlow()
                for try await settingsFromFlow in sequence {
                    self.settings = settingsFromFlow
                }
            } catch {
                print("Failed getting settings from repository flow: \(error)")
            }
        }
    }
    
    func setHasCompletedFirstRun(completed: Bool) {
        repository.setHasCompletedFirstRun(completed: completed)
    }

    func setEnabled(enabled: Bool) {
        repository.setEnabled(enabled: enabled)
    }

    func setBreakFrequency(frequency: Int64) {
        repository.setBreakFrequency(frequency: frequency)
    }

    func setBreakDuration(duration: Int64) {
        repository.setBreakDuration(duration: duration)
    }

    func setBreakSkip(skip: Bool) {
        repository.setBreakSkip(skip: skip)
    }

    func setBreakSnooze(snooze: Bool) {
        repository.setBreakSnooze(snooze: snooze)
    }

    func setBreakSnoozeLength(snoozeLength: Int64) {
        repository.setBreakSnoozeLength(snoozeLength: snoozeLength)
    }

    func setPopupNotification(popupNotification: Bool) {
        repository.setPopupNotification(popupNotification: popupNotification)
    }

    func setBreakMessage(message: String) {
        repository.setBreakMessage(message: message)
    }

    func setPrimaryColor(primary: String) {
        repository.setPrimaryColor(primary: primary)
    }

    func setTextColor(text: String) {
        repository.setTextColor(text: text)
    }

    func setResetOnIdle(reset: Bool) {
        repository.setResetOnIdle(reset: reset)
    }

    func setStartAtLogin(start: Bool) {
        LaunchAtLogin.isEnabled = start
        repository.setStartAtLogin(start: start)
    }
}

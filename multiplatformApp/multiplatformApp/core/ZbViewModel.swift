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
    private let repository = DefaultSettingsRepository()
    private let breakManager = DefaultBreakManager()
    private var notificationCenter = ZbNotificationCenter()
    
    @Published var settings: ZbSettings = ZbSettings.Companion.shared.default_
    
    init() {
        listenToSettingsFlow()
        initBreakManager()
    }
    
    private func initBreakManager() {
        breakManager.setAction { settings in
            if (settings.popupNotification) {
                
            } else {
                self.notificationCenter.send(
                    title: "Time for a break",
                    body: settings.breakMessage,
                    category: .breakStarted,
                    delay: nil
                ) {
                    self.notificationCenter.send(
                        title: "Break ended",
                        body: "The break has finished!",
                        category: .breakFinished,
                        delay: Double(settings.breakDuration)
                    ) {
                        self.breakManager.planBreak(snoozed: false)
                    }
                }
            }
        }
    }
    
    private func listenToSettingsFlow() {
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
    
    private func onNotificationAction(action: ZbNotification.Action) {
        switch action {
        case .skip:
            self.notificationCenter.cancelNotification(category: .breakFinished)
            self.breakManager.planBreak(snoozed: false)
        case .snooze:
            self.notificationCenter.cancelNotification(category: .breakFinished)
            self.breakManager.snoozeBreak()
        }
    }
    
    func setHasCompletedFirstRun(completed: Bool) {
        repository.setHasCompletedFirstRun(completed: completed)
    }

    func setEnabled(enabled: Bool) {
        repository.setEnabled(enabled: enabled)
        
        if (enabled) {
            self.breakManager.planBreak(snoozed: false)
        } else {
            self.breakManager.cancelBreak()
        }
    }

    func setBreakFrequency(frequency: Int64) {
        repository.setBreakFrequency(frequency: frequency)
        
        self.breakManager.planBreak(snoozed: false)
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

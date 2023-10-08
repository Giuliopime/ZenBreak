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
        self.notificationCenter.setActionHandler(handler: onNotificationAction)
    }
    
    private func initBreakManager() {
        breakManager.setAction { settings in
            if (settings.popupNotification) {
                self.showPopup()
            } else {
                self.notificationCenter.send(
                    title: "Time for a break",
                    body: settings.breakMessage,
                    category: .breakStarted,
                    delay: nil
                )
            }
        }
        
        breakManager.setEndedAction { settings in
            if (settings.popupNotification) {
                self.closePopup()
            } else {
                self.notificationCenter.send(
                    title: "Break ended",
                    body: "The break has finished!",
                    category: .breakFinished,
                    delay: nil
                )
            }
            
            self.breakManager.planBreak(snoozed: false)
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
            skipBreak()
        case .snooze:
            snoozeBreak()
        }
    }
    
    private func showPopup() {
        ZbAppDelegate.shared.showBreakWindow()
    }
    
    private func closePopup() {
        ZbAppDelegate.shared.hideBreakWindow()
    }
    
    func startBreak() {
        breakManager.startBreak()
    }
    
    func skipBreak() {
        breakManager.planBreak(snoozed: false)
        closePopup()
    }
    
    func snoozeBreak() {
        breakManager.snoozeBreak()
        closePopup()
    }
    
    func setHasCompletedFirstRun(completed: Bool) {
        repository.setHasCompletedFirstRun(completed: completed)
    }

    func setEnabled(enabled: Bool) {
        repository.setEnabled(enabled: enabled)
        
        if (enabled) {
            breakManager.planBreak(snoozed: false)
        } else {
            breakManager.cancelBreak()
        }
    }

    func setBreakFrequency(frequency: Int64) {
        repository.setBreakFrequency(frequency: frequency)
        
        breakManager.planBreak(snoozed: false)
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

    func setBreakSnoozeDuration(snoozeDuration: Int64) {
        repository.setBreakSnoozeDuration(snoozeDuration: snoozeDuration)
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

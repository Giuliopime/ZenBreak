//
//  NotificationsManager.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 09/09/23.
//
//  Credits: https://github.com/ivoronin/TomatoBar/blob/main/TomatoBar/Notifications.swift
//

import Foundation
import UserNotifications
import sharedCore

enum ZbNotification {
    enum Category: String {
        case breakStarted, breakFinished
    }

    enum Action: String {
        case skip, snooze
    }
}

typealias ZbNotificationHandler = (ZbNotification.Action) -> Void

class ZbNotificationCenter: NSObject, UNUserNotificationCenterDelegate {
    private var center = UNUserNotificationCenter.current()
    private var handler: ZbNotificationHandler?

    override init() {
        super.init()

        center.requestAuthorization(options: [.alert, .sound]) { _, error in
            if let error = error {
                print("Error requesting notification authorization: \(error)")
            }
        }

        center.delegate = self

        let actionSkip = UNNotificationAction(
            identifier: ZbNotification.Action.skip.rawValue,
            title: "Skip",
            options: []
        )
        let actionSnooze = UNNotificationAction(
            identifier: ZbNotification.Action.snooze.rawValue,
            title: "Snooze",
            options: []
        )
        
        let breakStartedCategory = UNNotificationCategory(
            identifier: ZbNotification.Category.breakStarted.rawValue,
            actions: [actionSkip, actionSnooze],
            intentIdentifiers: []
        )
        let breakFinishedCategory = UNNotificationCategory(
            identifier: ZbNotification.Category.breakFinished.rawValue,
            actions: [],
            intentIdentifiers: []
        )

        center.setNotificationCategories([
            breakStartedCategory,
            breakFinishedCategory,
        ])
    }

    func setActionHandler(handler: @escaping ZbNotificationHandler) {
        self.handler = handler
    }

    func userNotificationCenter(
        _: UNUserNotificationCenter,
        didReceive response: UNNotificationResponse,
        withCompletionHandler _: @escaping () -> Void
    ) {
        if handler != nil {
            if let action = ZbNotification.Action(rawValue: response.actionIdentifier) {
                handler!(action)
            }
        }
    }

    func send(
        title: String,
        body: String,
        category: ZbNotification.Category,
        delay: Double?
    ) {
        let content = UNMutableNotificationContent()
        content.title = title
        content.body = body
        content.categoryIdentifier = category.rawValue
        
        var trigger: UNNotificationTrigger? = nil
        if (delay != nil) {
            trigger = UNTimeIntervalNotificationTrigger(timeInterval: delay!, repeats: false)
        }
        
        let request = UNNotificationRequest(
            identifier: category.rawValue,
            content: content,
            trigger: trigger
        )
        
        center.add(request) { error in
            if (error != nil) {
                print("Error adding notification: \(error!)")
            }
        }
    }
}

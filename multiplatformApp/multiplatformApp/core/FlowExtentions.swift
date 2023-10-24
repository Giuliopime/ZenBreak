//
//  FlowExtentions.swift
//  multiplatformApp
//
//  Created by Giulio Pimenoff Verdolin on 04/09/23.
//

import Foundation
import KMPNativeCoroutinesAsync
import ZenBreakCoreKit

extension ISettingsRepository {
    func getSettingsFlow() -> NativeFlowAsyncSequence<ZbSettings, Error, KotlinUnit> {
        return asyncSequence(for: ISettingsRepositoryNativeKt.getSettingsFlow(self))
    }
}

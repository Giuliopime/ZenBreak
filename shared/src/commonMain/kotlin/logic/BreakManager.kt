package logic

import data.model.ZbSettings

interface BreakManager {
    suspend fun planBreak(zbSettings: ZbSettings)

    fun cancelBreak()
}
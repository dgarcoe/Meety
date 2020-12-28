package com.dgarcoe.meety.states

import com.dgarcoe.meety.MeetyMain

class ConfigurationState(app: MeetyMain) : MeetyState(app) {

    override fun returnMainMenu(): Int {
        app.currentState = app.mainMenuState
        app.startNewScreen(app.mainMenuScreen)
        return 0
    }
}
package com.dgarcoe.meety.states

import com.dgarcoe.meety.MeetyMain

class MainMenuState(app: MeetyMain) : MeetyState(app) {

    override fun createMeeting(): Int {
        app.currentState = app.createMeetingState
        app.startNewScreen(app.createMeetingScreen)
        return 0
    }

    override fun joinMeeting(): Int {
        app.currentState = app.joinMeetingState
        app.startNewScreen(app.joinMeetingScreen)
        return 0
    }

    override fun configureApp(): Int {
        app.currentState = app.configurationState
        app.startNewScreen(app.configureScreen)
        return 0
    }

}
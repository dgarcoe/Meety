package com.github.dgarcoe.states

import com.github.dgarcoe.MeetyMain

class MainMenuState(app: MeetyMain) : MeetyState(app) {

    override fun createMeeting(): Int {
        return 0
    }

    override fun joinMeeting(): Int {
        return 0
    }

    override fun configureApp(): Int {
        return 0
    }

}
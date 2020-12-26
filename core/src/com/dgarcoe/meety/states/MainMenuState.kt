package com.dgarcoe.meety.states

import com.dgarcoe.meety.MeetyMain

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
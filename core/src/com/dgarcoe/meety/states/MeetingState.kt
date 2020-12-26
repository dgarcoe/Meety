package com.dgarcoe.meety.states

import com.dgarcoe.meety.MeetyMain

class MeetingState(app: MeetyMain) : MeetyState(app) {

    override fun startTurn(): Int {
        return 0
    }

    override fun endTurn(): Int {
        return 0
    }

    override fun endMeeting(): Int {
        return 0
    }
}
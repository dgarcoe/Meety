package com.github.dgarcoe.states

import com.github.dgarcoe.MeetyMain

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
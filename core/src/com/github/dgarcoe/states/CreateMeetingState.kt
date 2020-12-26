package com.github.dgarcoe.states

import com.github.dgarcoe.MeetyMain

class CreateMeetingState(app: MeetyMain) : MeetyState(app) {

    override fun startMeeting(): Int {
        return 0
    }
}
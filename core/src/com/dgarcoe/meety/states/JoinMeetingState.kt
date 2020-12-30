package com.dgarcoe.meety.states

import com.dgarcoe.meety.MeetyMain

class JoinMeetingState(app: MeetyMain) : MeetyState(app) {

    override fun joinStartedMeeting(): Int {
        return 0
    }
}
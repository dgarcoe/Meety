package com.dgarcoe.meety.states

import com.dgarcoe.meety.Meeting
import com.dgarcoe.meety.MeetyMain

class CreateMeetingState(app: MeetyMain) : MeetyState(app) {

    override fun startMeeting(meeting: Meeting): Int {
        app.currentState = app.meetingState
        app.meetingScreen.meeting = meeting
        app.startNewScreen(app.meetingScreen)
        return 0
    }
}
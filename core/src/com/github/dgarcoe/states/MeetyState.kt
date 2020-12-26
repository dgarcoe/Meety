package com.github.dgarcoe.states

import com.github.dgarcoe.MeetyMain

abstract class MeetyState(val app:MeetyMain) {
    open fun createMeeting():Int = throw UnsupportedOperationException("Operation not supported")
    open fun joinMeeting():Int = throw UnsupportedOperationException("Operation not supported")
    open fun startMeeting():Int = throw UnsupportedOperationException("Operation not supported")
    open fun startTurn():Int = throw UnsupportedOperationException("Operation not supported")
    open fun endTurn():Int = throw UnsupportedOperationException("Operation not supported")
    open fun endMeeting():Int = throw UnsupportedOperationException("Operation not supported")
    open fun configureApp():Int = throw UnsupportedOperationException("Operation not supported")
    open fun returnMainMenu():Int = throw UnsupportedOperationException("Operation not supported")
}
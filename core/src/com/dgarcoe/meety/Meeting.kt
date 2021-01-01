package com.dgarcoe.meety

class Meeting(val type:String, val participants:Int, val turnTime:Int, val cost:Int) {

    var turnInProgress: Boolean = false
}
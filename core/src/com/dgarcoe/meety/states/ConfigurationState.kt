package com.dgarcoe.meety.states

import com.dgarcoe.meety.MeetyMain

class ConfigurationState(app: MeetyMain) : MeetyState(app) {

    override fun returnMainMenu(): Int {
        return 0
    }
}
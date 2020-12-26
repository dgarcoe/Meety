package com.github.dgarcoe.states

import com.github.dgarcoe.MeetyMain

class ConfigurationState(app: MeetyMain) : MeetyState(app) {

    override fun returnMainMenu(): Int {
        return 0
    }
}
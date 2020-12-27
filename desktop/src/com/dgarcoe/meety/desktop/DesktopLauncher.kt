package com.dgarcoe.meety.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.dgarcoe.meety.MeetyMain

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = 640
        config.height = 1280
        LwjglApplication(MeetyMain(), config)
    }
}
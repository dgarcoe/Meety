package com.github.dgarcoe

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.github.dgarcoe.states.*

class MeetyMain : ApplicationAdapter() {

    lateinit var currentState : MeetyState
    lateinit var currentScreen : Screen

    val mainMenuState : MainMenuState = MainMenuState(this)

    val createMeetingState : CreateMeetingState = CreateMeetingState(this)

    val joinMeetingState : JoinMeetingState = JoinMeetingState(this)

    val meetingState : MeetingState = MeetingState(this)

    val configurationState : ConfigurationState = ConfigurationState(this)


    lateinit var skin: Skin

    override fun create() {

        skin = Skin()
        skin.addRegions(TextureAtlas(Gdx.files.internal("skin/sgx/skin/sgx-ui.atlas")))
        skin.load(Gdx.files.internal("skin/sgx/skin/sgx-ui.json"))

        currentState = mainMenuState

    }
    override fun render() {}
    override fun dispose() {}
}
package com.dgarcoe.meety

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.dgarcoe.meety.screens.*
import com.dgarcoe.meety.states.*

class MeetyMain : Game() {

    lateinit var currentState : MeetyState
    lateinit var currentScreen : Screen

    val mainMenuState : MainMenuState = MainMenuState(this)
    lateinit var mainMenuScreen: MainMenuScreen

    val createMeetingState : CreateMeetingState = CreateMeetingState(this)
    lateinit var createMeetingScreen: CreateMeetingScreen

    val joinMeetingState : JoinMeetingState = JoinMeetingState(this)
    lateinit var joinMeetingScreen: JoinMeetingScreen

    val meetingState : MeetingState = MeetingState(this)
    lateinit var meetinScreen : MeetingScreen

    val configurationState : ConfigurationState = ConfigurationState(this)
    lateinit var configureScreen: ConfigureScreen


    lateinit var skin: Skin

    override fun create() {

        skin = Skin()
        skin.addRegions(TextureAtlas(Gdx.files.internal("skin/sgx/skin/sgx-ui.atlas")))
        skin.load(Gdx.files.internal("skin/sgx/skin/sgx-ui.json"))

        mainMenuScreen = MainMenuScreen(this, skin)
        createMeetingScreen = CreateMeetingScreen(this, skin)
        joinMeetingScreen = JoinMeetingScreen(this, skin)
        meetinScreen = MeetingScreen(this, skin)
        configureScreen = ConfigureScreen(this, skin)

        currentState = mainMenuState
        currentScreen = mainMenuScreen
        setScreen(currentScreen)

    }

    fun createMeeting() {
        currentState.createMeeting()
    }

    fun joinMeeting() {
        currentState.joinMeeting()
    }

    fun startMeeting() {
        currentState.startMeeting()
    }

    fun startTurn() {
        currentState.startTurn()
    }

    fun endTurn() {
        currentState.endTurn()
    }

    fun endMeeting() {
        currentState.endMeeting()
    }

    fun configureApp() {
        currentState.configureApp()
    }

    fun returnMainMenu() {
        currentState.returnMainMenu()
    }
}
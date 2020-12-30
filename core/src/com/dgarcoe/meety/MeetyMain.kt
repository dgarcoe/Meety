package com.dgarcoe.meety

import com.badlogic.gdx.*
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.dgarcoe.meety.renderers.MeetyRenderer
import com.dgarcoe.meety.screens.*
import com.dgarcoe.meety.states.*

class MeetyMain : Game() {

    var preferences : Preferences? = null

    lateinit var currentState : MeetyState
    lateinit var currentScreen : Screen

    val mainMenuState : MainMenuState = MainMenuState(this)
    lateinit var mainMenuScreen: MainMenuScreen

    val createMeetingState : CreateMeetingState = CreateMeetingState(this)
    lateinit var createMeetingScreen: CreateMeetingScreen

    val joinMeetingState : JoinMeetingState = JoinMeetingState(this)
    lateinit var joinMeetingScreen: JoinMeetingScreen

    val meetingState : MeetingState = MeetingState(this)
    lateinit var meetingScreen : MeetingScreen

    val configurationState : ConfigurationState = ConfigurationState(this)
    lateinit var configureScreen: ConfigureScreen

    lateinit var skin: Skin

    val renderer : MeetyRenderer = MeetyRenderer()

    override fun create() {

        val generatorTitle = FreeTypeFontGenerator(Gdx.files.internal("fonts/BitPap.ttf"))
        val generatorButtons = FreeTypeFontGenerator(Gdx.files.internal("fonts/minecraft.ttf"))
        val parameterTitle = FreeTypeFontGenerator.FreeTypeFontParameter()
        val parameterButtons = FreeTypeFontGenerator.FreeTypeFontParameter()

        if (Gdx.graphics.height>1500) {
            parameterTitle.size = 240
            parameterButtons.size = 50
        } else {
            parameterTitle.size = 140
            parameterButtons.size = 25
        }

        parameterTitle.color = Color.ORANGE
        parameterButtons.color = Color.BLACK

        val fontTitle = generatorTitle.generateFont(parameterTitle)
        generatorTitle.dispose()

        val fontButtons = generatorButtons.generateFont(parameterButtons)
        generatorButtons.dispose()

        preferences = Gdx.app.getPreferences("MeetyPreferences")

        skin = Skin()
        skin.add("font",fontButtons)
        skin.addRegions(TextureAtlas(Gdx.files.internal("skin/skin/metal-ui.atlas")))
        skin.load(Gdx.files.internal("skin/skin/metal-ui.json"))

        mainMenuScreen = MainMenuScreen(this, skin,fontTitle)
        createMeetingScreen = CreateMeetingScreen(this, skin)
        joinMeetingScreen = JoinMeetingScreen(this, skin)
        meetingScreen = MeetingScreen(this, skin)
        configureScreen = ConfigureScreen(this, skin)

        currentState = mainMenuState
        currentScreen = mainMenuScreen
        setScreen(currentScreen)

    }

    fun startNewScreen(screen:Screen) {
        currentScreen = screen
        super.setScreen(screen)
    }

    fun createMeeting() {
        currentState.createMeeting()
    }

    fun joinMeeting() {
        currentState.joinMeeting()
    }

    fun joinStartedMeeting() {
        currentState.joinStartedMeeting()
    }

    fun startMeeting(meeting: Meeting) {
        currentState.startMeeting(meeting)
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
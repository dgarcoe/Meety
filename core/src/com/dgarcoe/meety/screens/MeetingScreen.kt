package com.dgarcoe.meety.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.TimeUtils
import com.dgarcoe.meety.Meeting
import com.dgarcoe.meety.MeetyMain
import java.util.concurrent.TimeUnit

class MeetingScreen(val app: MeetyMain, val skin: Skin, val fontTitle: BitmapFont, val fontCounter: BitmapFont) : Screen, InputProcessor {

    lateinit var meeting : Meeting
    lateinit var stage: Stage
    lateinit var table: Table

    private val HEIGHT_TITLE_PERCENT = 0.1f
    private val HEIGHT_COUNTER_PERCENT = 0.4f
    private val WIDTH_BUTTON_PERCENT = 0.45f
    private val HEIGHT_BUTTON_PERCENT = 0.05f

    private var startedTime: Long = 0
    private var currentTurnTime: Long = 0
    private var totalTurnTime: Long = 0

    private lateinit var counter: Label

    private var beginCounter : Boolean = true
    private var endCounter : Boolean = false

    private fun initStage() {

        app.renderer.show()

        stage = Stage()
        table = Table(skin)

        table.setFillParent(true)
        Gdx.input.inputProcessor = stage
    }

    private fun setStage() {

        val buttonWidth = Gdx.graphics.width*WIDTH_BUTTON_PERCENT
        val buttonHeight = Gdx.graphics.height*HEIGHT_BUTTON_PERCENT

        meeting.turnInProgress = false

        val headingStyle = Label.LabelStyle()
        headingStyle.font = fontTitle

        val heading = Label("Meeting Room", headingStyle)

        val counterStyle = Label.LabelStyle()
        counterStyle.font = fontCounter

        currentTurnTime = (meeting.turnTime*60*1000).toLong()
        totalTurnTime = currentTurnTime

        val startTime = timeToString(currentTurnTime)

        counter = Label(startTime, counterStyle)
        counter.color = Color.GREEN

        val buttonTurn = TextButton("Start Turn", skin)
        buttonTurn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if (!meeting.turnInProgress) {
                    meeting.turnInProgress = true
                    buttonTurn.setText("Pause Turn")
                    startedTime = TimeUtils.millis()
                    beginCounter = false
                } else {
                    meeting.turnInProgress = false
                    buttonTurn.setText("Start Turn")
                }
            }
        })

        val buttonPassTurn = TextButton("Pass Turn", skin)
        buttonPassTurn.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {

            }
        })

        val buttonEndMeeting = TextButton("End Meeting", skin)
        buttonEndMeeting.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {

            }
        })

        table.top()
        table.add("").height(0f).row()
        table.add(heading).colspan(2).spaceTop(Gdx.graphics.height*HEIGHT_TITLE_PERCENT).expandX().row()
        table.add(counter).colspan(2).spaceTop(Gdx.graphics.height*HEIGHT_COUNTER_PERCENT).expandX().row()
        table.add(buttonTurn).colspan(2).width(buttonWidth).height(buttonHeight).spaceTop(55f).row()
        table.add(buttonPassTurn).colspan(2).width(buttonWidth).height(buttonHeight).spaceTop(15f).row()
        table.add(buttonEndMeeting).colspan(2).width(buttonWidth).height(buttonHeight).spaceTop(15f).row()

        stage.addActor(table)
    }

    override fun hide() {
        dispose()
        Gdx.input.inputProcessor = null
    }

    override fun show() {
        initStage()
        setStage()
    }

    override fun render(delta: Float) {
        app.renderer.render(delta)

        if (meeting.turnInProgress && !endCounter) {

            currentTurnTime -= TimeUtils.timeSinceMillis(startedTime)
            startedTime = TimeUtils.millis()
            counter.setText(timeToString(currentTurnTime))

            if (currentTurnTime/totalTurnTime.toFloat() < 0.4) {
               counter.color = Color.ORANGE
            }

            if (currentTurnTime/totalTurnTime.toFloat() < 0.1) {
                counter.color = Color.RED
            }

            if (currentTurnTime/totalTurnTime.toFloat() <= 0.0) {
                endCounter = true
            }
        }

        val progress = ((currentTurnTime/totalTurnTime.toFloat())*100).toInt()
        app.hourglassRenderer.render(delta, progress, beginCounter, endCounter)

        stage.act(delta)
        stage.draw()
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {
        app.renderer.resize(width, height)
    }

    override fun dispose() {
        stage.dispose()
    }

    private fun timeToString(millis: Long): String {
        val startTime = String.format("%02d : %02d",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        )
        return startTime
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun keyTyped(character: Char): Boolean {
        TODO("Not yet implemented")
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun keyUp(keycode: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun keyDown(keycode: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        TODO("Not yet implemented")
    }

}
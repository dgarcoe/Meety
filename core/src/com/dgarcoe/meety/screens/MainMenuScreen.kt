package com.dgarcoe.meety.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.dgarcoe.meety.MeetyMain

class MainMenuScreen(val app:MeetyMain, val skin: Skin, val fontTitle: BitmapFont) : Screen, InputProcessor {

    private val WIDTH_BUTTON_PERCENT = 0.45f
    private val HEIGHT_BUTTON_PERCENT = 0.05f
    private val HEIGHT_TITLE_PERCENT = 0.1f
    private val HEIGHT_BUTTONS_PERCENT = 0.4f

    lateinit var stage: Stage
    lateinit var table: Table

    private fun initStage() {

        app.renderer.show()
        app.hourglassRenderer.show()

        stage = Stage()
        table = Table(skin)

        table.setFillParent(true)
        Gdx.input.inputProcessor = stage
    }

    private fun setStage() {

        val headingStyle = Label.LabelStyle()
        headingStyle.font = fontTitle

        val heading = Label("Meety", headingStyle)

        val buttonCreateMeeting = TextButton("Create Meeting", skin)
        buttonCreateMeeting.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                app.createMeeting()
            }
        })

        val buttonJoinMeeting = TextButton("Join Meeting", skin)
        buttonJoinMeeting.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                app.joinMeeting()
            }
        })

        val buttonConfiguration = TextButton("Configuration", skin)
        buttonConfiguration.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                app.configureApp()
            }
        })

        val buttonWidth = Gdx.graphics.width*WIDTH_BUTTON_PERCENT
        val buttonHeight = Gdx.graphics.height*HEIGHT_BUTTON_PERCENT

        table.top()
        table.add("").height(0f).row()
        table.add(heading).spaceTop(Gdx.graphics.height*HEIGHT_TITLE_PERCENT).expandX().row()
        table.add(buttonCreateMeeting).width(buttonWidth).height(buttonHeight)
                .spaceTop(Gdx.graphics.height*HEIGHT_BUTTONS_PERCENT).row()
        table.add(buttonJoinMeeting).width(buttonWidth).height(buttonHeight).spaceTop(15f).row()
        table.add(buttonConfiguration).width(buttonWidth).height(buttonHeight).spaceTop(15f).row()

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
        app.hourglassRenderer.renderMainMenu(delta)

        stage.act(delta)
        stage.draw()
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {
        app.renderer.resize(width,height)
    }

    override fun dispose() {
        stage.dispose()
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
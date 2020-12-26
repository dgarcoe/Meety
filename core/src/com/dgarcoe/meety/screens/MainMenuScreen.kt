package com.dgarcoe.meety.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
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

class MainMenuScreen(val app:MeetyMain, val skin: Skin) : Screen, InputProcessor {

    private val WIDTH_CAMERA = 128
    private val HEIGHT_CAMERA = 256

    private val WIDTH_BUTTON_PERCENT = 0.45f
    private val HEIGHT_BUTTON_PERCENT = 0.05f

    lateinit var stage: Stage
    lateinit var table: Table

    var cam: OrthographicCamera? = null
    var viewPort: Viewport? = null

    private fun initStage() {

        val aspectRatio = Gdx.graphics.height/Gdx.graphics.width

        cam = OrthographicCamera(WIDTH_CAMERA.toFloat(), HEIGHT_CAMERA.toFloat())
        viewPort = FitViewport(WIDTH_CAMERA.toFloat()*aspectRatio, HEIGHT_CAMERA.toFloat(),cam)
        (viewPort as FitViewport).apply()
        cam!!.setToOrtho(false, WIDTH_CAMERA.toFloat(), HEIGHT_CAMERA.toFloat())
        cam!!.position.set((WIDTH_CAMERA/2).toFloat(), (HEIGHT_CAMERA/2).toFloat(),0f)
        cam!!.update()

        stage = Stage()
        table = Table(skin)

        table.setFillParent(true)
        Gdx.input.inputProcessor = stage
    }

    private fun setStage() {

        val headingStyle = Label.LabelStyle()

        val heading = Label("Sewer Car", headingStyle)

        val buttonCreateMeeting = TextButton("Play", skin)
        buttonCreateMeeting.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                app.createMeeting()
            }
        })
        buttonCreateMeeting.pad(15f)

        val buttonWidth = Gdx.graphics.width*WIDTH_BUTTON_PERCENT
        val buttonHeight = Gdx.graphics.height*HEIGHT_BUTTON_PERCENT

        table.add(heading).spaceBottom(100f).expandX().row()
        table.add(buttonCreateMeeting).width(buttonWidth).height(buttonHeight).spaceBottom(15f).row()

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
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        cam!!.update()

        stage.act(delta)
        stage.draw()
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun resize(width: Int, height: Int) {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        TODO("Not yet implemented")
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
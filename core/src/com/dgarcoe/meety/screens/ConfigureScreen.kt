package com.dgarcoe.meety.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.dgarcoe.meety.MeetyMain

class ConfigureScreen(val app: MeetyMain, val skin: Skin) : Screen, InputProcessor {

    lateinit var stage: Stage
    lateinit var table: Table

    private val WIDTH_BUTTON_PERCENT = 0.45f
    private val HEIGHT_BUTTON_PERCENT = 0.05f
    private val WIDTH_TEXT_PERCENT = 0.5f

    private fun initStage() {

        stage = Stage()
        table = Table(skin)

        table.setFillParent(true)
        Gdx.input.inputProcessor = stage
    }

    private fun setStage() {

        val username = Label("Username: ",skin)
        val mqttServerIP = Label("MQTT Broker IP: ",skin)
        val mqttServerPort = Label("MQTT Broker port: ",skin)

        val usernameTextField = TextField("", skin)
        usernameTextField.width = 40F
        usernameTextField.text = app.preferences!!.getString("USERNAME")
        val mqttServerIPTextField = TextField("", skin)
        mqttServerIPTextField.width = 40F
        mqttServerIPTextField.text = app.preferences!!.getString("MQTT_IP")
        val mqttServerPortTextField = TextField("", skin)
        mqttServerPortTextField.width = 40F
        mqttServerPortTextField.text = app.preferences!!.getString("MQTT_PORT")

        val buttonSave = TextButton("Save", skin)
        buttonSave.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                app.preferences!!.putString("USERNAME",usernameTextField.text)
                app.preferences!!.putString("MQTT_IP",mqttServerIPTextField.text)
                app.preferences!!.putString("MQTT_PORT",mqttServerPortTextField.text)
            }
        })

        val buttonBack = TextButton("Back", skin)
        buttonBack.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                app.returnMainMenu()
            }
        })

        val buttonWidth = Gdx.graphics.width*WIDTH_BUTTON_PERCENT
        val buttonHeight = Gdx.graphics.height*HEIGHT_BUTTON_PERCENT
        val textFieldWidth = Gdx.graphics.width*WIDTH_TEXT_PERCENT

        table.add(username).spaceBottom(15f)
        table.add(usernameTextField).width(textFieldWidth).spaceBottom(15f).row()
        table.add(mqttServerIP).spaceBottom(15f)
        table.add(mqttServerIPTextField).width(textFieldWidth).spaceBottom(15f).row()
        table.add(mqttServerPort).spaceBottom(15f)
        table.add(mqttServerPortTextField).width(textFieldWidth).spaceBottom(15f).row()
        table.add(buttonSave).colspan(2).width(buttonWidth).height(buttonHeight).spaceBottom(15f).row()
        table.add(buttonBack).colspan(2).width(buttonWidth).height(buttonHeight).spaceBottom(15f).row()

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
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        app.cam!!.update()

        stage.act(delta)
        stage.draw()
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {

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
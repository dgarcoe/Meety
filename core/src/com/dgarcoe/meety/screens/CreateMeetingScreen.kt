package com.dgarcoe.meety.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Scaling
import com.dgarcoe.meety.Meeting
import com.dgarcoe.meety.MeetyMain


class CreateMeetingScreen(val app: MeetyMain, val skin: Skin) : Screen, InputProcessor {

    lateinit var stage: Stage
    lateinit var table: Table

    private val WIDTH_BUTTON_PERCENT = 0.45f
    private val HEIGHT_BUTTON_PERCENT = 0.05f
    private val WIDTH_TABLE_PERCENT = 0.8f
    private val HEIGHT_CHECKBOX_PERCENT = 0.025f

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
        val checkBoxSize = Gdx.graphics.height*HEIGHT_CHECKBOX_PERCENT

        val type = Label("Meeting Type",skin)
        val turnTime = Label("Turn Time (min)",skin)
        val participants = Label("Participants",skin)
        val cost = Label("Cost per hour (1 person)",skin)

        val soloCheckbox = CheckBox("Solo",skin,"radio")
        soloCheckbox.image.setScaling(Scaling.fill)
        soloCheckbox.imageCell.size(checkBoxSize,checkBoxSize)
        val multiCheckbox = CheckBox("Multi",skin,"radio")
        multiCheckbox.image.setScaling(Scaling.fill)
        multiCheckbox.imageCell.size(checkBoxSize,checkBoxSize)

        val typeButtonGroup = ButtonGroup(soloCheckbox,multiCheckbox)

        val participantsTextField = TextField("", skin)
        participantsTextField.width = 20F
        participantsTextField.text = "0"
        participantsTextField.alignment = Align.center
        participantsTextField.maxLength = 2
        participantsTextField.setTextFieldFilter(TextFieldFilter { textField, c ->
            if (Character.toString(c).matches("^[0-9]+\$".toRegex())) {
                return@TextFieldFilter true
            } else
                return@TextFieldFilter false
        })

        val turnTimeTextField = TextField("", skin)
        turnTimeTextField.width = 20F
        turnTimeTextField.text = "0"
        turnTimeTextField.alignment = Align.center
        turnTimeTextField.maxLength = 2
        turnTimeTextField.setTextFieldFilter(TextFieldFilter { textField, c ->
            if (Character.toString(c).matches("^[0-9]+\$".toRegex())) {
                return@TextFieldFilter true
            } else
                return@TextFieldFilter false
        })

        val costTextField = TextField("", skin)
        costTextField.width = 20F
        costTextField.text = "0"
        costTextField.alignment = Align.center
        costTextField.maxLength = 3
        costTextField.setTextFieldFilter(TextFieldFilter { textField, c ->
            if (Character.toString(c).matches("^[0-9]+\$".toRegex())) {
                return@TextFieldFilter true
            } else
                return@TextFieldFilter false
        })

        val buttonStart = TextButton("Start Meeting", skin)
        buttonStart.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                var meeting = Meeting(typeButtonGroup.checked.text.toString(), participantsTextField.text.toInt(),
                        turnTimeTextField.text.toInt(), costTextField.text.toInt())
                app.startMeeting(meeting)
            }
        })


        table.add(type).spaceBottom(20f).align(Align.center).colspan(2).center().row()
        table.add(soloCheckbox).spaceBottom(55f).align(Align.center).expandX()
        table.add(multiCheckbox).spaceBottom(55f).align(Align.center).expandX().row()
        table.add(participants).spaceBottom(20f).align(Align.center).colspan(2).center().row()
        table.add(participantsTextField).spaceBottom(55f).align(Align.center).colspan(2).center().row()
        table.add(turnTime).spaceBottom(20f).align(Align.center).colspan(2).center().row()
        table.add(turnTimeTextField).spaceBottom(55f).align(Align.center).colspan(2).center().row()
        table.add(cost).spaceBottom(20f).align(Align.center).colspan(2).center().row()
        table.add(costTextField).spaceBottom(55f).align(Align.center).colspan(2).center().row()
        table.add(buttonStart).colspan(2).width(buttonWidth).height(buttonHeight).spaceBottom(15f).row()

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
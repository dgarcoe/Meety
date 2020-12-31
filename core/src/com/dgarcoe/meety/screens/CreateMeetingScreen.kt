package com.dgarcoe.meety.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Scaling
import com.dgarcoe.meety.Meeting
import com.dgarcoe.meety.MeetyMain


class CreateMeetingScreen(val app: MeetyMain, val skin: Skin, val fontTitle:BitmapFont) : Screen, InputProcessor {

    lateinit var stage: Stage
    lateinit var table: Table

    private val WIDTH_BUTTON_PERCENT = 0.45f
    private val HEIGHT_BUTTON_PERCENT = 0.05f
    private val HEIGHT_CHECKBOX_PERCENT = 0.025f
    private val HEIGHT_TITLE_PERCENT = 0.1f

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

        val headingStyle = Label.LabelStyle()
        headingStyle.font = fontTitle

        val heading = Label("Create Meeting", headingStyle)

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

        val dialog: Dialog = object : Dialog("", skin) {
            override fun result(obj: Any) {
                println("result $obj")
            }
        }
        dialog.text("Can't create a new meeting.")
        dialog.button("OK", true)


        val buttonStart = TextButton("Start Meeting", skin)
        buttonStart.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                if (validateInputs(typeButtonGroup,participantsTextField.text,turnTimeTextField.text,costTextField.text)) {
                    var meeting = Meeting(typeButtonGroup.checked.text.toString(), participantsTextField.text.toInt(),
                                            turnTimeTextField.text.toInt(), costTextField.text.toInt())
                    app.startMeeting(meeting)
                } else {
                    dialog.show(stage)
                }
            }
        })

        table.top()
        table.add("").height(0f).row()
        table.add(heading).colspan(2).spaceTop(Gdx.graphics.height*HEIGHT_TITLE_PERCENT).expandX().row()
        table.add(type).spaceTop(Gdx.graphics.height*HEIGHT_TITLE_PERCENT).align(Align.center).colspan(2).center().row()
        table.add(soloCheckbox).spaceTop(20f).align(Align.center).expandX()
        table.add(multiCheckbox).spaceTop(20f).align(Align.center).expandX().row()
        table.add(participants).spaceTop(20f).align(Align.center).colspan(2).center().row()
        table.add(participantsTextField).spaceTop(20f).align(Align.center).colspan(2).center().row()
        table.add(turnTime).spaceTop(20f).align(Align.center).colspan(2).center().row()
        table.add(turnTimeTextField).spaceTop(20f).align(Align.center).colspan(2).center().row()
        table.add(cost).spaceTop(20f).align(Align.center).colspan(2).center().row()
        table.add(costTextField).spaceTop(20f).align(Align.center).colspan(2).center().row()
        table.add(buttonStart).colspan(2).width(buttonWidth).height(buttonHeight).spaceTop(55f).row()

        stage.addActor(table)
    }

    private fun validateInputs(typeButtonGroup: ButtonGroup<CheckBox>, participants: String,
                               turnTime: String, cost: String) : Boolean {

        if (typeButtonGroup.checked == null) return false
        if (participants.toInt()<=0 || turnTime.toInt()<=0 || cost.toInt()<=0) return false

        return true
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
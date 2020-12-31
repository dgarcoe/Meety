package com.dgarcoe.meety.renderers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array


class HourglassRenderer(val atlas: TextureAtlas) {

    private val FRAME_DURATION = 0.5f

    lateinit var staticHourglassBegin: TextureRegion
    lateinit var staticHourglassEnd: TextureRegion

    lateinit var hourglassBegin : Animation<TextureRegion>
    lateinit var hourglassFirstInterval: Animation<TextureRegion>
    lateinit var hourglassSecondInterval: Animation<TextureRegion>
    lateinit var hourglassThirdInterval: Animation<TextureRegion>
    lateinit var hourglassFourthInterval: Animation<TextureRegion>
    lateinit var hourglassFifthInterval: Animation<TextureRegion>
    lateinit var hourglassSixthInterval: Animation<TextureRegion>

    private var spriteBatch: SpriteBatch? = null

    var stateTime : Float = 0f

    var hourglassSize = 0

    fun show() {

        hourglassSize = (Gdx.graphics.height*0.3f).toInt()

        staticHourglassBegin = atlas.findRegion("hourglass0000")
        staticHourglassEnd = atlas.findRegion("hourglass0014")

        val hourglassBeginFrames : Array<TextureRegion> = Array<TextureRegion>(2)
        for (i in 0..1) {
            hourglassBeginFrames.add(atlas.findRegion("hourglass000"+i.toString()))
        }
        hourglassBegin = Animation<TextureRegion>(FRAME_DURATION,hourglassBeginFrames)

        val hourglassFirstIntervalFrames : Array<TextureRegion> = Array<TextureRegion>(2)

        for (i in 2..3) {
            hourglassFirstIntervalFrames.add(atlas.findRegion("hourglass000"+i.toString()))
        }
        hourglassFirstInterval = Animation<TextureRegion>(FRAME_DURATION,hourglassFirstIntervalFrames)
        hourglassFirstInterval.playMode = Animation.PlayMode.LOOP

        val hourglassSecondIntervalFrames : Array<TextureRegion> = Array<TextureRegion>(2)

        for (i in 4..5) {
            hourglassSecondIntervalFrames.add(atlas.findRegion("hourglass000"+i.toString()))
        }
        hourglassSecondInterval = Animation<TextureRegion>(FRAME_DURATION,hourglassSecondIntervalFrames)
        hourglassSecondInterval.playMode = Animation.PlayMode.LOOP

        val hourglassThirdIntervalFrames : Array<TextureRegion> = Array<TextureRegion>(2)

        for (i in 6..7) {
            hourglassThirdIntervalFrames.add(atlas.findRegion("hourglass000"+i.toString()))
        }
        hourglassThirdInterval = Animation<TextureRegion>(FRAME_DURATION,hourglassThirdIntervalFrames)
        hourglassThirdInterval.playMode = Animation.PlayMode.LOOP

        val hourglassFourthIntervalFrames : Array<TextureRegion> = Array<TextureRegion>(2)

        for (i in 8..9) {
            hourglassFourthIntervalFrames.add(atlas.findRegion("hourglass000"+i.toString()))
        }
        hourglassFourthInterval = Animation<TextureRegion>(FRAME_DURATION,hourglassFourthIntervalFrames)
        hourglassFourthInterval.playMode = Animation.PlayMode.LOOP

        val hourglassFifthIntervalFrames : Array<TextureRegion> = Array<TextureRegion>(2)

        for (i in 10..11) {
            hourglassFifthIntervalFrames.add(atlas.findRegion("hourglass00"+i.toString()))
        }
        hourglassFifthInterval = Animation<TextureRegion>(FRAME_DURATION,hourglassFifthIntervalFrames)
        hourglassFifthInterval.playMode = Animation.PlayMode.LOOP

        val hourglassSixthIntervalFrames : Array<TextureRegion> = Array<TextureRegion>(2)

        for (i in 12..13) {
            hourglassSixthIntervalFrames.add(atlas.findRegion("hourglass00"+i.toString()))
        }
        hourglassSixthInterval = Animation<TextureRegion>(FRAME_DURATION,hourglassSixthIntervalFrames)
        hourglassSixthInterval.playMode = Animation.PlayMode.LOOP

        spriteBatch = SpriteBatch()
    }

    fun render(delta: Float) {


    }

    fun renderMainMenu(delta:Float) {
        spriteBatch!!.begin()
        stateTime += delta
        val currentFrame = hourglassSecondInterval.getKeyFrame(stateTime)
        spriteBatch!!.draw(currentFrame, Gdx.graphics.width/2f-hourglassSize/2,Gdx.graphics.height/2f-hourglassSize/3,
                hourglassSize*1f,hourglassSize*1f)
        spriteBatch!!.end()
    }

    fun dispose() {

    }

}



package com.dgarcoe.meety.renderers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport

class MeetyRenderer {

    private val WIDTH_CAMERA = 128
    private val HEIGHT_CAMERA = 256
    var cam: OrthographicCamera? = null
    var viewPort: Viewport? = null

    private val width: Int = 128
    private val height : Int = 256

    fun show() {
        val aspectRatio = Gdx.graphics.height/ Gdx.graphics.width

        cam = OrthographicCamera()
        viewPort = FitViewport(WIDTH_CAMERA.toFloat()*aspectRatio, HEIGHT_CAMERA.toFloat(),cam)
        (viewPort as FitViewport).apply()
        cam!!.setToOrtho(false, WIDTH_CAMERA.toFloat(), HEIGHT_CAMERA.toFloat())
        cam!!.position.set((WIDTH_CAMERA/2).toFloat(), (HEIGHT_CAMERA/2).toFloat(),0f)

        cam!!.update()
    }

    fun render(delta: Float) {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        cam!!.position.set(cam!!.position.x, cam!!.position.y, 0f)
        cam!!.update()
    }

    fun resize(width: Int, height: Int)  {
        viewPort!!.update(width, height)
        cam!!.position.set((WIDTH_CAMERA/2).toFloat(), (HEIGHT_CAMERA/2).toFloat(),0f)
    }

    fun dispose() {

    }
}
package com.anwesh.uiprojects.multilineballenderview

/**
 * Created by anweshmishra on 26/08/20.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Canvas

val colors : Array<String> = arrayOf("#673AB7", "#F44336", "#3F51B5", "#4CAF50", "#2196F3")
val parts : Int = 4
val scGap : Float = 0.02f / parts
val strokeFactor : Float = 90f
val sizeFactor : Float = 2.9f
val ballFactor : Float = 4.5f
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")
val lines : Int = 4
val totalRot : Float = 90f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n))
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawLineBallEnder(i : Int, scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = Math.min(w, h) / sizeFactor
    val r : Float = size / ballFactor
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, parts)
    val sf2 : Float = sf.divideScale(1, parts)
    val sf3 : Float = sf.divideScale(2, parts)
    val sf4 : Float = sf.divideScale(3, parts)
    val rotPart : Float = totalRot / (lines - 1)
    save()
    translate(w / 2, h / 2)
    rotate(-rotPart * i * sf3)
    drawLine(0f, 0f, size * sf1, 0f, paint)
    drawCircle(size * sf4, 0f, r * sf2, paint)
    restore()
}

fun Canvas.drawMultiLineBallEnder(scale : Float, w : Float, h : Float, paint : Paint) {
    for (j in 0..(lines - 1)) {
        drawLineBallEnder(j, scale, w, h, paint)
    }
}

fun Canvas.drawMLBENode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = Color.parseColor(colors[i])
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawMultiLineBallEnder(scale, w, h, paint)
}

class MultiLineBallEnderView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN  -> {

            }
        }
        return true
    }
}
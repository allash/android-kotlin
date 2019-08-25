package com.home.android.piperbike.util

import kotlin.math.cos
import kotlin.math.sin

object MathUtils {
    fun getPointX(centerX: Float, distance: Float, degrees: Float): Float {
        return (centerX + distance * sin(-degrees * Math.PI / 180 + Math.PI / 2)).toFloat()
    }

    fun getPointY(centerY: Float, distance: Float, degrees: Float): Float {
        return (centerY + distance * cos(-degrees * Math.PI / 180 + Math.PI / 2)).toFloat()
    }
}
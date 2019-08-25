package com.home.android.piperbike.views.utils

import android.graphics.Path
import android.graphics.RectF
import com.home.android.piperbike.util.MathUtils.getPointX
import com.home.android.piperbike.util.MathUtils.getPointY

object PathUtils {
    fun getArcPath(
        arcPath: Path,
        outerCircleBounds: RectF,
        innerCircleBounds: RectF,
        startAngle: Float,
        sweepAngle: Float
    ): Path {
        arcPath.reset()

        // Move to start point
        val startX = getPointX(
            innerCircleBounds.centerX(),
            innerCircleBounds.width() / 2f,
            startAngle
        )
        val startY = getPointY(
            innerCircleBounds.centerY(),
            innerCircleBounds.height() / 2f,
            startAngle
        )

        arcPath.moveTo(startX, startY)

        arcPath.addArc(innerCircleBounds, startAngle, sweepAngle)

        // Line from inner to outer arc
        arcPath.lineTo(
            getPointX(
                outerCircleBounds.centerX(),
                outerCircleBounds.width() / 2f,
                startAngle + sweepAngle
            ),
            getPointY(
                outerCircleBounds.centerY(),
                outerCircleBounds.height() / 2f,
                startAngle + sweepAngle
            )
        )

        // Add outer arc
        arcPath.addArc(outerCircleBounds, startAngle + sweepAngle, -sweepAngle)

        // Close (drawing last line and connecting arcs)
        arcPath.lineTo(startX, startY)

        return arcPath
    }
}
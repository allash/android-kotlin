package com.home.android.piperbike.views.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.home.android.piperbike.R
import com.home.android.piperbike.views.utils.PathUtils

class WeeklyGoalProgressView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    companion object {
        private const val ARC_PATH_START_ANGLE = -90f
        private const val OUTER_CIRCLE_MARGIN = 10f
        private const val INNER_CIRCLE_MARGIN = 40f
        private const val BACKGROUND_CIRCLE_STROKE_WIDTH = INNER_CIRCLE_MARGIN - OUTER_CIRCLE_MARGIN
        private const val BACKGROUND_CIRCLE_RADIUS_OFFSET = BACKGROUND_CIRCLE_STROKE_WIDTH / 2f
        private const val FULL_ANGLE = 3.6f
        private const val PROGRESS_ANIMATION_DURATION = 3000L
    }

    private var arcProgressPath = Path()

    private val outerCircleBounds = RectF()
    private val innerCircleBounds = RectF()

    private var circleX: Float = 0f
    private var circleY: Float = 0f

    private var weeklyGoalCompletedValue: Float = 0f
    private var weeklyGoalEstimatedValue: Int = 0

    private val paintProgressArcPath = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        isAntiAlias = true
    }

    private val paintBackgroundCircle = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorSecondary)
        strokeWidth = BACKGROUND_CIRCLE_STROKE_WIDTH
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private val paintGoalCompleted = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorWhite)
        textSize = 70f
        textAlign = Paint.Align.CENTER
    }

    private val paintGoalEstimated = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorWhite)
        textSize = 40f
        textAlign = Paint.Align.CENTER
    }

    private var sweepAngle = 90f

    init {
        outerCircleBounds.left = OUTER_CIRCLE_MARGIN
        outerCircleBounds.top = OUTER_CIRCLE_MARGIN

        innerCircleBounds.left = INNER_CIRCLE_MARGIN
        innerCircleBounds.top = INNER_CIRCLE_MARGIN
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        outerCircleBounds.right = width - OUTER_CIRCLE_MARGIN
        outerCircleBounds.bottom = height - OUTER_CIRCLE_MARGIN

        innerCircleBounds.right = width - INNER_CIRCLE_MARGIN
        innerCircleBounds.bottom = height - INNER_CIRCLE_MARGIN
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null) return

        circleX = width / 2f
        circleY = height / 2f

        canvas.drawCircle(
            circleX,
            circleY,
            circleX - OUTER_CIRCLE_MARGIN - BACKGROUND_CIRCLE_RADIUS_OFFSET,
            paintBackgroundCircle
        )

        arcProgressPath.reset()
        arcProgressPath.moveTo(circleX, circleY)
        arcProgressPath = PathUtils.getArcPath(
            arcPath = arcProgressPath,
            outerCircleBounds = outerCircleBounds,
            innerCircleBounds = innerCircleBounds,
            startAngle = ARC_PATH_START_ANGLE,
            sweepAngle = sweepAngle
        )
        canvas.drawPath(arcProgressPath, paintProgressArcPath)

        canvas.drawText("${weeklyGoalCompletedValue}km", circleX, circleY, paintGoalCompleted)
        canvas.drawText("${weeklyGoalEstimatedValue}km goal", circleX, circleY + 60f, paintGoalEstimated)
    }

    fun calculateProgress(estimated: Int, completed: Float) {
        if (completed > estimated)
            throw IllegalArgumentException("Completed value cannot be greater than the estimated value")

        weeklyGoalEstimatedValue = estimated
        weeklyGoalCompletedValue = completed

        invalidate()

        val percentage = completed * 100f / estimated
        val angle = percentage * FULL_ANGLE
        createAnimator(angle)
    }

    private fun createAnimator(angle: Float) {
        val animator = ValueAnimator.ofFloat(0f, angle)
        animator.duration = PROGRESS_ANIMATION_DURATION
        animator.interpolator = FastOutSlowInInterpolator()
        animator.addUpdateListener {
            sweepAngle = animator.animatedValue as Float
            invalidate()
        }

        animator.start()
    }
}
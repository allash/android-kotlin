package com.home.android.piperbike.data.activities.model

data class DtoGetWeeklyGoalResponse(
    val title: String,
    val estimated: Int,
    val completed: Float,
    val remainingDays: Int
)
package com.home.android.piperbike.data.activities.model

import java.util.Date
import java.util.UUID

data class DtoGetActivityResponse (
    var id: UUID,
    val name: String,
    val description: String?,
    val distance: Float?,
    val elapsedTime: Int?,
    val createdAt: Date?,
    val derp: String
)
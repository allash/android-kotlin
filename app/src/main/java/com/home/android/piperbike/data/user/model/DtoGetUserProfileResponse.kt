package com.home.android.piperbike.data.user.model

import java.util.*

data class DtoGetUserProfileResponse (
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val email: String
)
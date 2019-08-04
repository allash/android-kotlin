package com.home.android.piperbike.util

import android.content.Context
import android.content.SharedPreferences

object LocalStorage {

    private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME"
    const val X_AUTH_TOKEN = "x-auth-token"

    var token: String? = null

    fun saveString(ctx: Context, key: String, value: String) {
        val editor = getEditor(ctx)
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(ctx: Context, key: String, defaultValue: String? = null): String? {
        val preferences = getPreferences(ctx)
        return preferences.getString(key, defaultValue)
    }

    private fun getPreferences(ctx: Context): SharedPreferences {
        return ctx.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    private fun getEditor(ctx: Context): SharedPreferences.Editor {
        return getPreferences(ctx).edit()
    }
}
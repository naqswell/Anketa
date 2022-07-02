package com.example.anketa.data

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val FILE_PREFS = "prefsAnketa"
    private val PREFS_ROLE = "prefsRole"

    private val appContext = context.applicationContext
    private val preferences: SharedPreferences = appContext.getSharedPreferences(FILE_PREFS, Context.MODE_PRIVATE)

    var role: Role
        get() = Role.valueOf(preferences.getString(PREFS_ROLE, Role.Empty.toString())!!)
        set(value) = preferences.edit().putString(PREFS_ROLE, value.toString()).apply()
}
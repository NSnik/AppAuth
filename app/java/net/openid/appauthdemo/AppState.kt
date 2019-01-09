package com.example.demofon

import android.content.Context
import android.content.SharedPreferences

class AppState(context: Context) {

    private val preference: SharedPreferences

    companion object {
        private const val EXTRA_TOKEN = "extra_token"
    }

    init {
        preference = context.getSharedPreferences("private", Context.MODE_PRIVATE)
    }

    fun getToken() = preference.getString(EXTRA_TOKEN, null)

    fun saveToken(token: String?) {
        val edit = preference.edit()
        edit.putString(EXTRA_TOKEN, token)
        edit.apply()
    }

    fun logout() {
        saveToken(null)
    }

}
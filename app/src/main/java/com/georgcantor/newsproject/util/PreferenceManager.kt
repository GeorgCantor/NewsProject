package com.georgcantor.newsproject.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.georgcantor.newsproject.view.MainActivity.Companion.MY_PREFS
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferenceManager(activity: Activity) {

    private val gson = Gson()
    private var json = ""

    private val prefs: SharedPreferences = activity.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE)

    fun saveBoolean(key: String, value: Boolean) = prefs.edit().putBoolean(key, value).apply()

    fun saveString(key: String, value: String) = prefs.edit().putString(key, value).apply()

    fun saveInt(key: String, value: Int) = prefs.edit().putInt(key, value).apply()

    fun saveTags(key: String, categories: ArrayList<String>) {
        json = gson.toJson(categories)
        prefs.edit().putString(key, json).apply()
    }

    fun getBoolean(key: String): Boolean = prefs.getBoolean(key, false)

    fun getString(key: String): String? = prefs.getString(key, "")

    fun getInt(key: String): Int = prefs.getInt(key, 0)

    fun getTags(key: String): ArrayList<String>? {
        val type = object : TypeToken<ArrayList<String>>() {}.type
        val json = prefs.getString(key, "")

        return gson.fromJson(json, type)
    }

}
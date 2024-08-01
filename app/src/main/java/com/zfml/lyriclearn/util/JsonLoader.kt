package com.zfml.lyriclearn.util

import android.content.Context
import com.google.gson.Gson
import java.lang.reflect.Type

class JsonLoader(private val context: Context) {

    fun <T> loadFromJsonFile(fileName: String, type: Type): List<T> {
        val jsonFileString = getJsonDataFromAsset(fileName)
        return Gson().fromJson(jsonFileString, type)
    }

    private fun getJsonDataFromAsset(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}
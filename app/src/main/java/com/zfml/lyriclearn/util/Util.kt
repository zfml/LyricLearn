package com.zfml.lyriclearn.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zfml.lyriclearn.domain.model.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
   val jsonString: String
   try {
       jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
   }catch (ioException: IOException) {
       ioException.printStackTrace()
       return null
   }
    return jsonString
}





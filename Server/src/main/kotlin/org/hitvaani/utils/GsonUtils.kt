package org.hitvaani.utils

import com.google.gson.Gson

object GsonUtils {

    val gson = Gson()

    fun toJsonString(obj: Any): String {
        return gson.toJson(obj)
    }

    inline fun <reified T> fromJsonString(json: String): T? {
        try {
            return gson.fromJson(json, T::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}

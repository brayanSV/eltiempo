package com.user.brayan.eltiempo.api

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.user.brayan.eltiempo.model.ItemsNewsCollections
import com.user.brayan.eltiempo.model.News
import com.user.brayan.eltiempo.model.NewsColletions
import com.user.brayan.eltiempo.utils.AbsentLiveData
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


class ConvertJsonToData  {
    fun convert(response: String): List<News> {
        val json = JSONObject(response)
        val json2: JSONObject = json.getJSONObject("collection")

        var list:MutableList<News> = arrayListOf()
        val turns = Gson().fromJson(json2.toString(), NewsColletions::class.java)

        turns.collection.forEach {
            val data = it.data
            val links = it.links

           /* list.add(
                nasaId = it.data.nasaId
            )*/


            Log.e("datos","$data")
        }



        return list.toList()
    }

   /* fun <T> convert(response: String, clazz: Class<T>): List<T> {
        val json = JSONObject(response)
        val json2: JSONObject = json.getJSONObject("collection")

        val turns = Gson().fromJson(json2.toString(), clazz)
        Log.e("datos", "$turns")

        return listOf(turns)
    }
    }*/
}
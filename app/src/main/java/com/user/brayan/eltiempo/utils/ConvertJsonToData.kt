package com.user.brayan.eltiempo.utils

import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import com.user.brayan.eltiempo.R
import com.user.brayan.eltiempo.model.News
import com.user.brayan.eltiempo.model.NewsCollections
import org.json.JSONObject


class ConvertJsonToData  {
    fun convert(response: String): List<News> {
        val json = JSONObject(response)
        val json2: JSONObject = json.getJSONObject("collection")

        val list: MutableList<News> = mutableListOf()
        val turns = Gson().fromJson(json2.toString(), NewsCollections::class.java)

        turns.collection.forEach {
            var linkPhoto = ""

            it.links?.forEach { photo ->
                Log.e("daots", "$photo")

                if (photo.rel != null && photo.rel.isNotEmpty()) {
                    if (photo.rel == "preview" && photo.href != null ) {
                        linkPhoto = photo.href
                        return@forEach
                    }
                }
            }



            list.add(
                News(
                    href = it.href!!,
                    data = News.DataItemsNews(
                        dateCreated = it.data[0].dateCreated,
                        description = it.data[0].description,
                        nasaId = it.data[0].nasaId,
                        keywords = it.data[0].keywords,
                        center = it.data[0].center,
                        title = it.data[0].title,
                        mediaType = it.data[0].mediaType,
                        photo = linkPhoto,
                        favorite = false
                    )
                )
            )
        }

        Log.e("datos", "${list.toList()}")

        return list.toList()
    }

}
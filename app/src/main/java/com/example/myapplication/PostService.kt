package com.example.myapplication

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.Serializable
import java.net.URL

class PostService {

    object PostGenerator{
        data class PostData(val id: String,
                            val title: String,
                            val body: String): Serializable

        fun fromApiData(apiData: String): PostData{
            //This is where you get the JSON object



            val (id, title, body  ) = apiData.split(",")
            return PostData(id, title, body)
        }


    }

    fun fetchPostData(Url: String): Deferred<String> {

        return GlobalScope.async {
            val apiData = URL(Url).readText()
            apiData

        }
    }
}
package com.example.myapplication

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class PostService {
    fun fetchPostData(Url: String): Deferred<String> {

        return GlobalScope.async {
            val apiData = URL(Url).readText()
            apiData
        }
    }
}
package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


private const val POST_DATA_API = "https://jsonplaceholder.typicode.com/posts"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            var postService = PostService()

            var postData = postService.fetchPostData(POST_DATA_API).await()

            text_view_json.text = postData
        }
    }
}


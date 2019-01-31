package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


private const val POST_DATA_API = "https://jsonplaceholder.typicode.com/todos/1"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = ApiService()

        GlobalScope.launch(Dispatchers.Main) {
            val response = apiService.getpost().await()
            text_view_json.text = response.title.toString()
        }
    }
}


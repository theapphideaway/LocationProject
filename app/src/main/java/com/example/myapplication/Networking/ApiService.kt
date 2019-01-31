package com.example.myapplication.Networking

import com.example.myapplication.Model.CommentResponse
import com.example.myapplication.Model.PostResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    //https://jsonplaceholder.typicode.com/comments

    @GET("/todos/1")
    fun getpost(): Deferred<PostResponse>

    @GET("/comments")
    fun getComments(): Deferred<List<CommentResponse>>

}
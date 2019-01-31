package com.example.myapplication

data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
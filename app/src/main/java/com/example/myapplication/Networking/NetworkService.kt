package com.example.myapplication.Networking

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    private var mInstance: NetworkService? = null
    private val BASE_URL = "https://jsonplaceholder.typicode.com"
    private var mRetrofit: Retrofit? = null

    init {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getJSONApi(): ApiService {
        return mRetrofit!!.create<ApiService>(ApiService::class.java!!)
    }
}
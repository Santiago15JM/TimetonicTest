package com.sjm.timetonic.config

import com.google.gson.GsonBuilder
import com.sjm.timetonic.logic.ApiDefinitions
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val baseUrl = "https://timetonic.com/live/api.php"
    fun getInstance(): ApiDefinitions {
        val client = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiDefinitions::class.java)
    }
}

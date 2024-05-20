package com.aital.doskaykt

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : DoskaYktApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://doska.ykt.ru/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DoskaYktApi::class.java)
    }
}
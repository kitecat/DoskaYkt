package com.aital.doskaykt

import com.aital.doskaykt.models.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DoskaYktApi {
    @GET("feed?")
    fun getFeed(
        @Query("cid") cid : String, // категория
        @Query("sid") sid : String, // подкатегория
        @Query("rid") rid : String // рубрика
    ) : Call<Posts>
}
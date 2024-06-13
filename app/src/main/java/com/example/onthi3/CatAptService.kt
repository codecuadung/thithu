package com.example.onthi3

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatAptService {
    @GET("cats")
    suspend fun getCats(
        @Query("tags") tags: String = "cute",
        @Query("skip") skip:Int = 0,
        @Query("limit") limit: Int  = 10
    ):List<Cat>

    companion object{
        private const val BASE_URL = "https://cataas.com/api/"
        fun create():CatAptService{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(CatAptService::class.java)
        }
    }
}

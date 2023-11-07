package io.coderangers.podcastlibrary.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {
    private const val BASE_URL = "https://listen-api-test.listennotes.com/api/v2/"

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : PodcastApi = getRetrofit().create(PodcastApi::class.java)

}
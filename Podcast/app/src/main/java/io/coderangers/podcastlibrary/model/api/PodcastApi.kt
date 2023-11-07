package io.coderangers.podcastlibrary.model.api

import io.coderangers.podcastlibrary.model.BestPodcastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PodcastApi {

    @GET("best_podcasts")
    fun getCharacters(): Call<BestPodcastResponse>
}
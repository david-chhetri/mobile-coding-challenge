package io.coderangers.podcast.model.api

import io.coderangers.podcast.model.api.response.BestPodcastResponse
import retrofit2.Call
import retrofit2.http.GET


interface PodcastApi {
    @GET("best_podcasts")
    fun getPodcasts(): Call<BestPodcastResponse>
}
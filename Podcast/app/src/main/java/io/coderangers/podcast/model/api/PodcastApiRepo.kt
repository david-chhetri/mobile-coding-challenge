package io.coderangers.podcast.model.api

import androidx.compose.runtime.mutableStateOf
import io.coderangers.podcast.model.api.response.BestPodcastResponse
import io.coderangers.podcast.model.api.response.Podcast
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PodcastApiRepo(private val api: PodcastApi) {
    val bestPodcasts = MutableStateFlow<NetworkResult<BestPodcastResponse>>(NetworkResult.Initial())
    val podcastSingle = mutableStateOf<Podcast?>(null)

    fun getBestPodcasts() {
        bestPodcasts.value = NetworkResult.Loading()
        api.getPodcasts()
            .enqueue(object : Callback<BestPodcastResponse> {
                override fun onResponse(
                    call: Call<BestPodcastResponse>,
                    response: Response<BestPodcastResponse>
                ) {
                    if (response.isSuccessful)
                        response.body()?.let {
                            bestPodcasts.value = NetworkResult.Success(it)
                        }
                    else
                        bestPodcasts.value = NetworkResult.Error(response.message())
                }

                override fun onFailure(call: Call<BestPodcastResponse>, t: Throwable) {
                    t.localizedMessage?.let {
                        bestPodcasts.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            })

    }

    fun getSinglePodcast(id: String?) {
        id?.let {
            podcastSingle.value = bestPodcasts.value.data?.podcasts?.firstOrNull { podcast ->
                podcast.id == id

            }
        }


    }
}
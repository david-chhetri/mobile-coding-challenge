package io.coderangers.podcastlibrary.model.api

import androidx.compose.runtime.mutableStateOf
import io.coderangers.podcastlibrary.model.BestPodcastResponse
import io.coderangers.podcastlibrary.model.Podcast
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PodcastApiRepo (private val api: PodcastApi){
    val characters = MutableStateFlow<NetworkResult<BestPodcastResponse>>(NetworkResult.Initial())
    val characterDetails = mutableStateOf<Podcast?>(null)

    fun query(){
        characters.value = NetworkResult.Loading()
        api.getCharacters()
            .enqueue(object:Callback<BestPodcastResponse>{

                override fun onResponse(
                    call: Call<BestPodcastResponse>,
                    response: Response<BestPodcastResponse>
                ) {
                    if(response.isSuccessful)
                        response.body()?.let {
                            characters.value = NetworkResult.Success(it)
                        }
                    else
                        characters.value = NetworkResult.Error(response.message())
                }

                override fun onFailure(call: Call<BestPodcastResponse>, t: Throwable) {
                    t.localizedMessage?.let {
                        characters.value = NetworkResult.Error(it)
                    }
                    t.printStackTrace()
                }
            })

    }



    fun getSingleCharacter(id: String?){
        id?.let {
            characterDetails.value = characters.value.data?.podcasts?.single(){character ->
                character.id == id
            }
        }


    }


}
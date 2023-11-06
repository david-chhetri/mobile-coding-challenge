package io.coderangers.podcast.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.coderangers.podcast.model.api.PodcastApiRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BestPodcastsApiViewModel @Inject constructor(
    private val repo: PodcastApiRepo
): ViewModel() {
    val result = repo.bestPodcasts
    val singlePodcast = repo.podcastSingle

    init {
        retrievePodcasts()
    }

    private fun retrievePodcasts() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getBestPodcasts()
        }
    }

    fun retrieveSinglePodcast(id: String){
        repo.getSinglePodcast(id)
    }

}
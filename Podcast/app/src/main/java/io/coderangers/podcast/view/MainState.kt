package io.coderangers.podcast.view

import io.coderangers.podcast.model.api.response.BestPodcastResponse


sealed class MainState{
    object Idle: MainState()
    object Loading: MainState()
    data class Podcasts(val podcasts: List<BestPodcastResponse>): MainState()
    data class Error(val error: String?): MainState()
}

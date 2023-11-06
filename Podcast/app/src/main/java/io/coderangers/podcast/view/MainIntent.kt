package io.coderangers.podcast.view



sealed class MainIntent{
    object FetchPodcasts: MainIntent()
    object FetchPodcastDetail: MainIntent()
}

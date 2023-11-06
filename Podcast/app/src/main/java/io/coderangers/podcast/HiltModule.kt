package io.coderangers.podcast

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.coderangers.podcast.model.api.ApiService
import io.coderangers.podcast.model.api.PodcastApiRepo

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun providesApiRepo() = PodcastApiRepo(ApiService.api)

}
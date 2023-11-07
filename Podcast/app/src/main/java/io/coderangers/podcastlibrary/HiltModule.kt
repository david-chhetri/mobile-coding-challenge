package io.coderangers.podcastlibrary

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.coderangers.podcastlibrary.model.api.ApiService
import io.coderangers.podcastlibrary.model.api.PodcastApiRepo
import io.coderangers.podcastlibrary.model.db.CharacterDao
import io.coderangers.podcastlibrary.model.db.PodcastDb
import io.coderangers.podcastlibrary.model.db.CollectionDbRepo
import io.coderangers.podcastlibrary.model.db.CollectionDbRepoImpl
import io.coderangers.podcastlibrary.model.db.Constants.DB

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun providesApiRepo() = PodcastApiRepo(ApiService.api)

    @Provides
    fun provideCollectionDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context = context, PodcastDb::class.java, DB).build()

    @Provides
    fun provideCharacterDao(collectionDb: PodcastDb) = collectionDb.characterDao()

    @Provides
    fun provideDbRepoImpl(characterDao: CharacterDao): CollectionDbRepo =
        CollectionDbRepoImpl(characterDao)

}
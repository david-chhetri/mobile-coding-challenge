package io.coderangers.podcastlibrary.model.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [DbCharacter::class], version = 1, exportSchema = false)
abstract class PodcastDb: RoomDatabase(){

    abstract fun characterDao(): CharacterDao

}

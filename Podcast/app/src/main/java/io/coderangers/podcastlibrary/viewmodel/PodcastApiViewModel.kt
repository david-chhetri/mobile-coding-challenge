package io.coderangers.podcastlibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.coderangers.podcastlibrary.model.api.PodcastApiRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastApiViewModel @Inject constructor(
    private val repo: PodcastApiRepo
) : ViewModel() {

    val result = repo.characters
    val queryText = MutableStateFlow("")
    private val queryInput = Channel<String>(Channel.CONFLATED)
    val characterDetails = repo.characterDetails


    init {
        retrieveCharacters()
    }

    private fun retrieveCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.query()

        }
    }

    private fun validateQuery(query: String): Boolean = query.length >= 2

    fun onQueryUpdate(input: String){
        queryText.value = input
        queryInput.trySend(input)
    }

    fun retrieveSingleCharacter(id: String){
        repo.getSingleCharacter(id)
    }


}














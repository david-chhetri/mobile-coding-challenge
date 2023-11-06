package io.coderangers.podcast.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import io.coderangers.podcast.viewmodel.BestPodcastsApiViewModel


@Composable
fun PodcastDetailScreen(
    navController: NavHostController,
    vm: BestPodcastsApiViewModel,
    paddingValues: PaddingValues
){
    Text("Podcast detail Screen")
}
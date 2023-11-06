package io.coderangers.podcast

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.coderangers.podcast.ui.theme.PodcastTheme
import io.coderangers.podcast.view.PodcastDetailScreen
import io.coderangers.podcast.view.PodcastMainScreen
import io.coderangers.podcast.viewmodel.BestPodcastsApiViewModel

sealed class Destination(val route: String){
    object PodcastMain: Destination("main")
    object PodcastDetail: Destination("character/{podcastId}"){
        fun createRoute(podcastId: Int?)= "character/$podcastId"
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val lvm by viewModels<BestPodcastsApiViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PodcastTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    PodcastsScaffold(navController = navController, lvm)

                    
                }
            }
        }
    }
}

@Composable
fun PodcastsScaffold(navController: NavHostController, lvm: BestPodcastsApiViewModel){
    val scaffoldState = rememberScaffoldState()
    val ctx = LocalContext.current
    Scaffold ( modifier = Modifier,
        scaffoldState = scaffoldState

    ){paddingValues ->
        NavHost(navController = navController, startDestination = Destination.PodcastMain.route ){
            composable(Destination.PodcastMain.route){
                PodcastMainScreen(navController, lvm, paddingValues)

            }
    /*        composable(Destination.Collection.route){
                CollectionScreen()
            }*/
            composable(Destination.PodcastDetail.route) {navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getString("characterId")?.toString()
                if(id == null)
                    Toast.makeText(ctx,"Character id is required", Toast.LENGTH_SHORT ).show()
                else{

                    lvm.retrieveSinglePodcast(id)
                    PodcastDetailScreen(navController, lvm, paddingValues)

                }


            }
        }

    }

}
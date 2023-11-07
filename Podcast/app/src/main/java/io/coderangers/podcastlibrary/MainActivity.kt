package io.coderangers.podcastlibrary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.coderangers.podcastlibrary.ui.theme.ComicsLibraryTheme
import io.coderangers.podcastlibrary.view.CharacterDetailScreen
import io.coderangers.podcastlibrary.view.LibraryScreen
import io.coderangers.podcastlibrary.viewmodel.PodcastApiViewModel

sealed class Destination(val route: String) {
    object Library : Destination("library")
    object Collection : Destination("collection")
    object CharacterDetail : Destination("character/{characterId}") {
        fun createRoute(characterId: String) = "character/$characterId"
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val lvm by viewModels<PodcastApiViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComicsLibraryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    CharactersScaffold(navController = navController, lvm)


                }
            }
        }
    }
}

@Composable
fun CharactersScaffold(navController: NavHostController, lvm: PodcastApiViewModel) {
    val scaffoldState = rememberScaffoldState()
    val ctx = LocalContext.current
    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState,
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Destination.Library.route) {
            composable(Destination.Library.route) {
                LibraryScreen(navController, lvm, paddingValues)

            }
            composable(Destination.CharacterDetail.route) { navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getString("characterId")?.toString()
                if (id == null)
                    Toast.makeText(ctx, "Character id is required", Toast.LENGTH_SHORT).show()
                else {
                    lvm.retrieveSingleCharacter(id)
                    CharacterDetailScreen(
                        lvm = lvm,
                        paddingValues = paddingValues,
                        navController = navController
                    )

                }


            }
        }

    }

}


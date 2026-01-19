package fr.enssat.sharemybook.BastienLucasZakaria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.enssat.sharemybook.BastienLucasZakaria.ui.screens.LibraryListScreen
import fr.enssat.sharemybook.BastienLucasZakaria.ui.screens.LibraryScreen
import fr.enssat.sharemybook.BastienLucasZakaria.ui.theme.ShareMyBookTheme
import fr.enssat.sharemybook.BastienLucasZakaria.ui.viewmodel.LibraryViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ShareMyBookTheme {
                val navController = rememberNavController()
                val viewModel: LibraryViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "libraryList"
                ) {

                    composable("libraryList") {
                        LibraryListScreen(
                            viewModel = viewModel,
                            onNavigateToLibrary = { libraryId ->
                                //viewModel.selectLibrary(libraryId)
                                navController.navigate("libraryDetail")
                            }
                        )
                    }

                    composable("libraryDetail") {
                        // CORRECTION 1 : On récupère la bibliothèque sélectionnée depuis le ViewModel
                        val selectedLibrary by viewModel.selectedLibrary.collectAsState()

                        // On s'assure que la bibliothèque n'est pas null avant d'afficher l'écran
                        selectedLibrary?.let { library ->
                            LibraryScreen(
                                // CORRECTION 2 : On passe la bibliothèque à l'écran
                                library = library,
                                viewModel = viewModel,
                                // CORRECTION 3 : Le nom du paramètre est 'onNavigateBack'
                                onNavigateBack = {
                                    navController.popBackStack()
                                },
                                onAddBook = {
                                    // La logique pour scanner un livre viendra ici
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

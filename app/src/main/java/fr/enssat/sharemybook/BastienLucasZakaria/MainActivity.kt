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
import fr.enssat.sharemybook.BastienLucasZakaria.ui.AppViewModelProvider
import fr.enssat.sharemybook.BastienLucasZakaria.ui.book.BookEntryScreen
import fr.enssat.sharemybook.BastienLucasZakaria.ui.screens.MainLibraryScreen
import fr.enssat.sharemybook.BastienLucasZakaria.ui.theme.ShareMyBookTheme
import fr.enssat.sharemybook.BastienLucasZakaria.ui.viewmodel.LibraryViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ShareMyBookTheme {
                val navController = rememberNavController()
                // Attention : assure-toi de passer le repository au ViewModel via une Factory
                // ou un conteneur d'injection de dépendances comme AppViewModelProvider dans ton projet
                val viewModel: LibraryViewModel = viewModel(factory = AppViewModelProvider.Factory)

                NavHost(navController = navController, startDestination = "main") {

                    // ÉCRAN DE DÉMARRAGE : La liste des livres
                    composable("main") {
                        MainLibraryScreen(
                            viewModel = viewModel,
                            onManualEntry = { navController.navigate("bookEntry") }
                        )
                    }

                    // ÉCRAN DE SAISIE MANUELLE
                    composable("bookEntry") {
                        BookEntryScreen()
                    }
                }
            }
        }
    }
}

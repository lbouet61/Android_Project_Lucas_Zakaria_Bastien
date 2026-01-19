package fr.enssat.sharemybook.BastienLucasZakaria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

                // ViewModel principal
                val viewModel: LibraryViewModel = viewModel()
                val selectedLibrary by viewModel.selectedLibrary.collectAsState()

                // Navigation simple entre les écrans
                if (selectedLibrary == null) {
                    LibraryListScreen(viewModel)
                } else {
                    LibraryScreen(
                        library = selectedLibrary!!,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShareMyBookTheme {
        Greeting("Android")
    }
}
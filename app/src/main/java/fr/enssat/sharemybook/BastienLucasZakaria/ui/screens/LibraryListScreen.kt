package fr.enssat.sharemybook.BastienLucasZakaria.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import fr.enssat.sharemybook.BastienLucasZakaria.ui.components.CreateLibraryDialog
import fr.enssat.sharemybook.BastienLucasZakaria.ui.viewmodel.LibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryListScreen(
    viewModel: LibraryViewModel,
    onNavigateToLibrary: (String) -> Unit
) {
    val libraries by viewModel.libraries.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mes bibliothèques") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = { showDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Créer une bibliothèque"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(libraries) { library ->
                ListItem(
                    headlineContent = { Text(library.name) },
                    modifier = Modifier.clickable {
                        onNavigateToLibrary(library.id)
                    }
                )
                Divider()
            }
        }

        if (showDialog) {
            CreateLibraryDialog(
                onCreate = { libraryName ->
                    viewModel.createLibrary(libraryName)
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}

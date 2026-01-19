package fr.enssat.sharemybook.BastienLucasZakaria.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.enssat.sharemybook.BastienLucasZakaria.data.Book
import fr.enssat.sharemybook.BastienLucasZakaria.ui.viewmodel.LibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLibraryScreen(
    viewModel: LibraryViewModel,
    onManualEntry: () -> Unit
) {
    val books by viewModel.allBooks.collectAsState()

    // État pour afficher/masquer l'AlertDialog
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ma Bibliothèque") },
                actions = {
                    // LE BOUTON EN HAUT
                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Ajouter livre")
                    }
                }
            )
        }
    ) { padding ->
        // LISTE DES LIVRES
        if (books.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Aucun livre. Cliquez sur 'Ajouter livre' pour commencer.")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(padding).fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(books) { book ->
                    BookCard(book)
                }
            }
        }

        // --- LA PETITE FENÊTRE (AlertDialog) ---
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "Ajouter un livre")
                },
                text = {
                    Text("Choisissez la méthode d'ajout :")
                },
                confirmButton = {
                    // Bouton SCANNER
                    Button(
                        onClick = {
                            showDialog = false
                            // analyse() // Fonction du collègue
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Scanner le livre (ISBN)")
                    }
                },
                dismissButton = {
                    // Bouton À LA MAIN
                    OutlinedButton(
                        onClick = {
                            showDialog = false
                            onManualEntry() // Lance BookEntryScreen
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("À la main")
                    }
                }
            )
        }
    }
}

@Composable
fun BookCard(book: Book) {
    ElevatedCard {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = book.titre, style = MaterialTheme.typography.titleMedium, maxLines = 2)
            Spacer(Modifier.height(4.dp))
            Text(text = "ISBN: ${book.isbn}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
package fr.enssat.sharemybook.BastienLucasZakaria.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.enssat.sharemybook.BastienLucasZakaria.ui.components.BookCover
import fr.enssat.sharemybook.BastienLucasZakaria.domain.model.Library
import fr.enssat.sharemybook.BastienLucasZakaria.ui.viewmodel.LibraryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    library: Library,
    viewModel: LibraryViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(library.name) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.goBackToList() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                },
                actions = {
                    Button(onClick = { viewModel.onAddBookClicked() }) {
                        Text("Ajouter un livre")
                    }
                }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(padding)
        ) {
            items(library.books.size) { index ->
                BookCover(
                    coverUrl = library.books[index].coverUrl,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

package fr.enssat.sharemybook.BastienLucasZakaria.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.sharemybook.BastienLucasZakaria.data.Book
import fr.enssat.sharemybook.BastienLucasZakaria.data.BookRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class LibraryViewModel(private val repository: BookRepository) : ViewModel() {

    // On récupère le flux de tous les livres de la BDD
    val allBooks: StateFlow<List<Book>> = repository.getAllBooksStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // On garde cette fonction pour le bouton "Ajouter"
    fun onAddBookClicked() {
        // Logique de scan gérée par le collègue
    }
}

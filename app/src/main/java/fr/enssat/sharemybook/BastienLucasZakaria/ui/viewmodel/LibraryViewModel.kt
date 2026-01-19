package fr.enssat.sharemybook.BastienLucasZakaria.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import fr.enssat.sharemybook.BastienLucasZakaria.domain.model.Library

class LibraryViewModel : ViewModel() {

    private val _libraries = MutableStateFlow<List<Library>>(emptyList())
    val libraries: StateFlow<List<Library>> = _libraries

    private val _selectedLibrary = MutableStateFlow<Library?>(null)
    val selectedLibrary: StateFlow<Library?> = _selectedLibrary

    fun createLibrary(name: String) {
        val newLibrary = Library(name = name)
        _libraries.value = _libraries.value + newLibrary
    }

    fun selectLibrary(library: Library) {
        _selectedLibrary.value = library
    }

    fun goBackToList() {
        _selectedLibrary.value = null
    }

    fun onAddBookClicked() {
        // PLUS TARD : lancer le scan ISBN
        println("Ajouter un livre (scan à implémenter)")
    }
}

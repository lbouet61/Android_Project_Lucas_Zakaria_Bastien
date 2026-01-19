package fr.enssat.sharemybook.BastienLucasZakaria.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.enssat.sharemybook.BastienLucasZakaria.data.remote.OpenLibraryApi
import fr.enssat.sharemybook.BastienLucasZakaria.domain.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddBookViewModel : ViewModel() {

    private val api = OpenLibraryApi()

    private val _book = MutableStateFlow<Book?>(null)
    val book: StateFlow<Book?> = _book

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun fetchBook(isbn: String) {
        viewModelScope.launch {
            _loading.value = true
            _book.value = api.getBookByIsbn(isbn)
            _loading.value = false
        }
    }
}

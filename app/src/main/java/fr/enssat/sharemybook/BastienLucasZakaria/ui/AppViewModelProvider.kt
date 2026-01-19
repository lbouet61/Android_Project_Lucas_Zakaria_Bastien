package fr.enssat.sharemybook.BastienLucasZakaria.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import fr.enssat.sharemybook.BastienLucasZakaria.shareMyBookApplication
import fr.enssat.sharemybook.BastienLucasZakaria.ui.book.BookEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Initializer for BookEntryViewModel
        initializer {
            BookEntryViewModel(shareMyBookApplication().container.bookRepository)
        }
    }
}
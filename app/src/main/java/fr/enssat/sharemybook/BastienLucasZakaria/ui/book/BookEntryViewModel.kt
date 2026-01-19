package fr.enssat.sharemybook.BastienLucasZakaria.ui.book

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fr.enssat.sharemybook.BastienLucasZakaria.data.Book
import fr.enssat.sharemybook.BastienLucasZakaria.data.BookRepository

class BookEntryViewModel(private val bookRepository: BookRepository) : ViewModel() {
    /**
     * Holds current item ui state
     */
    var bookUiState by mutableStateOf(BookUiState())
        private set


    /**
     * Updates the [bookUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(bookDetails: BookDetails) {
        bookUiState =
            BookUiState(bookDetails = bookDetails, isEntryValid = validateInput(bookDetails))
    }

    /**
     * Inserts a [Book] in the Room database
     */
    suspend fun saveBook() {
        if (validateInput()) {
            bookRepository.insertBook(bookUiState.bookDetails.toBook())
        }
    }

    private fun validateInput(uiState: BookDetails = bookUiState.bookDetails): Boolean {
        return with(uiState) {
            isbn.isNotBlank() && titre.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for a Book.
 */
data class BookUiState(
    val bookDetails: BookDetails = BookDetails(),
    val isEntryValid: Boolean = false
)

data class  BookDetails(
    val id: Int = 0,
    val isbn: String = "",
    val titre: String = "",
)

/**
 * Extension function to convert [BookDetails] to [Book].
 */
fun BookDetails.toBook(): Book = Book(
    id = id,
    isbn = isbn,
    titre = titre,
)

/**
 * Extension function to convert [Book] to [BookUiState]
 */
fun Book.toItemUiState(isEntryValid: Boolean = false): BookUiState = BookUiState(
    bookDetails = this.toBookDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Book] to [BookDetails]
 */
fun Book.toBookDetails(): BookDetails = BookDetails(
    id = id,
    isbn = isbn,
    titre = titre,
)
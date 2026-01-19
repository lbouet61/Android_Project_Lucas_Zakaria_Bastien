package fr.enssat.sharemybook.BastienLucasZakaria.data.remote

import fr.enssat.sharemybook.BastienLucasZakaria.domain.model.Book

class OpenLibraryApi {

    private val service = OpenLibraryService()

    suspend fun getBookByIsbn(isbn: String): Book? {
        val response = service.fetchBookByIsbn(isbn) ?: return null
        return OpenLibraryMapper.toBook(response, isbn)
    }
}

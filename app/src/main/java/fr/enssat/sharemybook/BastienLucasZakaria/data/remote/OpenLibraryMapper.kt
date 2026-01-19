package fr.enssat.sharemybook.BastienLucasZakaria.data.remote

import fr.enssat.sharemybook.BastienLucasZakaria.data.model.OpenLibraryResponse
import fr.enssat.sharemybook.BastienLucasZakaria.domain.model.Book

object OpenLibraryMapper {

    fun toBook(response: OpenLibraryResponse, isbn: String): Book? {
        val recordEntry = response.records.entries.firstOrNull()
        val bookData = recordEntry?.value?.data ?: return null

        val authors = bookData.authors
            ?.joinToString(", ") { it.name ?: "" }
            ?.ifBlank { "Auteur inconnu" }
            ?: "Auteur inconnu"

        val coverUrl = response.items
            .firstOrNull()
            ?.cover
            ?.medium

        return Book(
            isbn = isbn,
            title = bookData.title ?: "Titre inconnu",
            authors = authors,
            coverUrl = coverUrl
        )
    }
}

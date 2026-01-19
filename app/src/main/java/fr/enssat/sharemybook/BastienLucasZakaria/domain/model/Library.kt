package fr.enssat.sharemybook.BastienLucasZakaria.domain.model

import java.util.UUID

data class Library(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val books: List<Book> = emptyList()
)

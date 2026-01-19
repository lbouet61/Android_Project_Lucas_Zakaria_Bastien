package fr.enssat.sharemybook.BastienLucasZakaria.domain.model

data class Book(
    val isbn: String,
    val title: String,
    val authors: String,
    val coverUrl: String?
)

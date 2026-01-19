package fr.enssat.sharemybook.BastienLucasZakaria.data.model

import kotlinx.serialization.Serializable

@Serializable
data class OpenLibraryResponse(
    val items: List<Item> = emptyList(),
    val records: Map<String, Record> = emptyMap()
)

@Serializable
data class Item(
    val cover: Cover? = null,
    val fromRecord: String? = null
)

@Serializable
data class Cover(
    val small: String? = null,
    val medium: String? = null,
    val large: String? = null
)

@Serializable
data class Record(
    val data: BookData? = null
)

@Serializable
data class BookData(
    val title: String? = null,
    val authors: List<Author>? = null
)

@Serializable
data class Author(
    val name: String? = null
)

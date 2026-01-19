package fr.enssat.sharemybook.BastienLucasZakaria.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isbn: String,
    val titre: String
)
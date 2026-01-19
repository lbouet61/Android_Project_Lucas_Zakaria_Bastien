package fr.enssat.sharemybook.BastienLucasZakaria.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books ORDER BY titre ASC")
    fun getAllBooks(): Flow<List<Book>>

    @Query("SELECT * from books WHERE id = :id")
    fun getBook(id: Int): Flow<Book?>

    // TODO: Add other queries

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Update
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)
}
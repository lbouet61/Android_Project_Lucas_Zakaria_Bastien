package fr.enssat.sharemybook.BastienLucasZakaria.data

import kotlinx.coroutines.flow.Flow

interface BookRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllBooksStream(): Flow<List<Book>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getBookStream(id: Int): Flow<Book?>

    /**
     * Insert item in the data source
     */
    suspend fun insertBook(item: Book)

    /**
     * Delete item from the data source
     */
    suspend fun deleteBook(item: Book)

    /**
     * Update item in the data source
     */
    suspend fun updateBook(item: Book)

}
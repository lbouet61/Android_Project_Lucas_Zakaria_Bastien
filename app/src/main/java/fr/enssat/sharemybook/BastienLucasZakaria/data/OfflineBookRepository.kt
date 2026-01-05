package fr.enssat.sharemybook.BastienLucasZakaria.data

import kotlinx.coroutines.flow.Flow

class OfflineBookRepository(private val bookDao: BookDao) : BookRepository {
    override fun getAllBooksStream(): Flow<List<Book>> = bookDao.getAllBooks()

    override fun getBookStream(id: Int): Flow<Book?> = bookDao.getBook(id)

    override suspend fun insertBook(item: Book) = bookDao.insert(item)

    override suspend fun deleteBook(item: Book) = bookDao.delete(item)

    override suspend fun updateBook(item: Book) = bookDao.update(item)
}
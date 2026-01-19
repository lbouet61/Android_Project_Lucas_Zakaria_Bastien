package fr.enssat.sharemybook.BastienLucasZakaria.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1)
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var Instance: LibraryDatabase? = null

        fun getDatabase(context: Context): LibraryDatabase {

            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, LibraryDatabase::class.java, "library_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
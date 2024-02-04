package jp.gcreate.sample.multiprojectgradlesample.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Book::class],
    version = 1,
)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}

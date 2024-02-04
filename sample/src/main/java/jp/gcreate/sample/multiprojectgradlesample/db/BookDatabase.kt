package jp.gcreate.sample.multiprojectgradlesample.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Book::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
    ]
)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}

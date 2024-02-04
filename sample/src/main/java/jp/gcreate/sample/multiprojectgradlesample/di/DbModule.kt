package jp.gcreate.sample.multiprojectgradlesample.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.gcreate.sample.multiprojectgradlesample.db.BookDao
import jp.gcreate.sample.multiprojectgradlesample.db.BookDatabase

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BookDatabase {
        return Room.databaseBuilder(
            context,
            BookDatabase::class.java,
            "book_database"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideBookDao(db: BookDatabase): BookDao = db.bookDao()
}
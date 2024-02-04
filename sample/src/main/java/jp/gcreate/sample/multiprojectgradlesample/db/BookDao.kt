package jp.gcreate.sample.multiprojectgradlesample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Query("select * from book")
    fun getAll():List<Book>

    @Query("select * from book where title=:title")
    fun get(title: String): Book?

    @Insert
    fun insert(target: Book)
}
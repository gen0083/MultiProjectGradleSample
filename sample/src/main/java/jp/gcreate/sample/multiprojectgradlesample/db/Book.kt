package jp.gcreate.sample.multiprojectgradlesample.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo val title: String,
    @ColumnInfo val author: String,
    @ColumnInfo val price: Int,
    @ColumnInfo(defaultValue = "2024") val year: Int,
)

package `in`.summachar.android.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "databasearticle")
data class DatabaseArticle(
    @PrimaryKey
    val typeOfArticle: Int,
    val articles:String?
)
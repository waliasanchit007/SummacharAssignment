package `in`.summachar.android.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticlesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: DatabaseArticle)

    @Query("SELECT articles FROM databasearticle WHERE typeOfArticle= :type")
    fun getArticlesThroughType(type: Int):LiveData<String?>

    @Query("SELECT * FROM databasearticle LIMIT 1")
    fun getAllDatabase(): List<DatabaseArticle>?
}

@Database(entities = [DatabaseArticle::class], version = 1)
abstract class ArticlesDatabase:RoomDatabase(){
    abstract val articleDao:ArticlesDao
}

private lateinit var INSTANCE:ArticlesDatabase

fun getDatabase(context: Context) : ArticlesDatabase{
    synchronized(ArticlesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ArticlesDatabase::class.java,
                    "articles").build()
        }
    }
    return INSTANCE

}
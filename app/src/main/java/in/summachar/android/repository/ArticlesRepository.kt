package `in`.summachar.android.repository

import `in`.summachar.android.database.ArticlesDatabase
import `in`.summachar.android.database.DatabaseArticle
import `in`.summachar.android.models.Article
import `in`.summachar.android.network.ArticleRepo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.room.TypeConverters
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.ParameterizedType

class ArticlesRepository(private val database: ArticlesDatabase) {
//    lateinit var top:LiveData<String?>
//    lateinit var indian:LiveData<String?>
//    lateinit var business:LiveData<String?>
//    lateinit var entertainment:LiveData<String?>
//    lateinit var health: LiveData<String?>
     //randomdfgd

     suspend fun checkIfDatabaseIsEmpty(): Boolean{
        return withContext(Dispatchers.IO){ database.articleDao.getAllDatabase().isNullOrEmpty() }
    }

    //Type converter for converting JSON tO POJO

    val moshi = Moshi.Builder().build()
    val listMydData : ParameterizedType = Types.newParameterizedType(List::class.java,Article::class.java)
    val jsonAdapter: JsonAdapter<List<Article>?> = moshi.adapter(listMydData)


    val top= Transformations.map(database.articleDao.getArticlesThroughType(1)){
        it?.let{ jsonAdapter.fromJson(it) }
    }
    val indian= Transformations.map(database.articleDao.getArticlesThroughType(2)){
        it?.let{ jsonAdapter.fromJson(it) }

    }
    val business=Transformations.map(database.articleDao.getArticlesThroughType(3)){
        it?.let{ jsonAdapter.fromJson(it) }

    }
    val entertainment= Transformations.map(database.articleDao.getArticlesThroughType(4)){
        it?.let{ jsonAdapter.fromJson(it) }

    }
    val health = Transformations.map(database.articleDao.getArticlesThroughType(5)){
        it?.let{ jsonAdapter.fromJson(it) }
    }



//    val articleFetchedFromApi= MutableLiveData<Boolean>(false)
    val refreshIsClicked = MutableLiveData<Boolean>(false)

//    fun refreshDatabase(){
//        refreshIsClicked.postValue(true)
//    }

//     suspend fun refreshDataInDatabase() {
//
//         val list = networkCall()
//
//         if(checkIfDatabaseIsEmpty()){
//
//         }

//        //fetch data from network
//             val topStories = ArticleRepo.getTopStoriesArticles()?.let { jsonAdapter.toJson(it) }
//             val indianStories = ArticleRepo.getIndianArticles()?.let { jsonAdapter.toJson(it) }
//             val businessArticles = ArticleRepo.getBusinessArticles()?.let { jsonAdapter.toJson(it) }
//             val entertainmentArticles = ArticleRepo.getEntertainmentArticles()?.let { jsonAdapter.toJson(it) }
//             val healthArticles = ArticleRepo.getHealthArticles()?.let { jsonAdapter.toJson(it) }
//
//        //insert fetched data into database
//        articleFetchedFromApi.postValue(true)

//        if(checkIfDatabaseIsEmpty()) {
//            withContext(Dispatchers.IO) {
//                Log.i("TAG", "inside refresh articles outside")
//                topStories.let { database.articleDao.insertArticles(DatabaseArticle(1, topStories)) }
//                indianStories.let { database.articleDao.insertArticles(DatabaseArticle(2, indianStories)) }
//                businessArticles.let { database.articleDao.insertArticles(DatabaseArticle(3, businessArticles)) }
//                entertainmentArticles.let { database.articleDao.insertArticles(DatabaseArticle(4, entertainmentArticles)) }
//                healthArticles.let { database.articleDao.insertArticles(DatabaseArticle(5, healthArticles)) }
//            }
//        }
//
//            Log.i("TAG","inside refresh is clidked")
//                withContext(Dispatchers.IO) {
//                    Log.i("TAG", "inside refresh articles inside")
//                    topStories.let { database.articleDao.insertArticles(DatabaseArticle(1, topStories)) }
//                    indianStories.let { database.articleDao.insertArticles(DatabaseArticle(2, indianStories)) }
//                    businessArticles.let { database.articleDao.insertArticles(DatabaseArticle(3, businessArticles)) }
//                    entertainmentArticles.let { database.articleDao.insertArticles(DatabaseArticle(4, entertainmentArticles)) }
//                    healthArticles.let { database.articleDao.insertArticles(DatabaseArticle(5, healthArticles)) }
//        }
//     }

//    suspend fun firstTimeAfterInstallation(){
//        if(checkIfDatabaseIsEmpty()){
//            val list = networkCall()
//            saveNetworkDataToDataBase(list)
//        }
//    }

    suspend fun networkCall() :List<String?>{
        val topStories = ArticleRepo.getTopStoriesArticles()?.let { jsonAdapter.toJson(it) }
        val indianStories = ArticleRepo.getIndianArticles()?.let { jsonAdapter.toJson(it) }
        val businessArticles = ArticleRepo.getBusinessArticles()?.let { jsonAdapter.toJson(it) }
        val entertainmentArticles = ArticleRepo.getEntertainmentArticles()?.let { jsonAdapter.toJson(it) }
        val healthArticles = ArticleRepo.getHealthArticles()?.let { jsonAdapter.toJson(it) }

        //insert fetched data into database
//        articleFetchedFromApi.postValue(true)
        return listOf(topStories,indianStories,businessArticles,entertainmentArticles,healthArticles)
    }

    suspend fun saveNetworkDataToDataBase(articlesTopic:List<String?>){
        withContext(Dispatchers.IO) {
            Log.i("TAG", "dataBase is refreshed")
            articlesTopic[0].let { database.articleDao.insertArticles(DatabaseArticle(1, it)) }
            articlesTopic[1].let { database.articleDao.insertArticles(DatabaseArticle(2, it)) }
            articlesTopic[2].let { database.articleDao.insertArticles(DatabaseArticle(3, it)) }
            articlesTopic[3].let { database.articleDao.insertArticles(DatabaseArticle(4, it)) }
            articlesTopic[4].let { database.articleDao.insertArticles(DatabaseArticle(5, it)) }
        }

    }
}

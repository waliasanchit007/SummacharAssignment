package `in`.summachar.android.network

import `in`.summachar.android.models.Article
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

object ArticleRepo {

    //hljljjhkjhjk,.mjkjkhkjhdfgdfhuiughrgrtyrrerfter
    suspend fun getTopStoriesArticles():List<Article>?{
        Log.i("TAG", "network call")

        return try{
            withContext(Dispatchers.IO){
                ApiClient.api.getTopHeadlines().body()?.articles
            }

        }catch (networkError: IOException){
            Log.e("Network Error", "${networkError}")
            null
        }
    }
    suspend fun getIndianArticles() :List<Article>?{

        return try{
            withContext(Dispatchers.IO) { ApiClient.api.getTopHeadlines(country = "in").body()?.articles
            }
        }catch (networkError: IOException){
            Log.e("Network Error", "${networkError}")
            return null
        }
    }
    suspend fun getBusinessArticles():List<Article>?{

        return try{
            withContext(Dispatchers.IO){
                ApiClient.api.getTopHeadlines(category = "business").body()?.articles
            }

        }catch (networkError: IOException){
            Log.e("Network Error", "${networkError}")
            null
        }
    }
    suspend fun getEntertainmentArticles():List<Article>?{

        return try{
            withContext(Dispatchers.IO) {
                ApiClient.api.getTopHeadlines(category = "entertainment").body()?.articles
            }

        }catch (networkError: IOException){
            Log.e("Network Error", "${networkError}")
            return null
        }
    }
    suspend fun getHealthArticles():List<Article>?{

        return try{
            withContext(Dispatchers.IO){
                ApiClient.api.getTopHeadlines(category = "health").body()?.articles
            }

        }catch (networkError: IOException){
            Log.e("Network Error", "${networkError}")
            null
        }
    }
}

package `in`.summachar.android

import `in`.summachar.android.network.ApiClient
import `in`.summachar.android.network.ArticleRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `know if api is responding`(){
        runBlocking {
            val article = withContext(Dispatchers.IO){ApiClient.api.getTopHeadlines().body()?.articles}
            assertNotNull(article)
        }
    }

    @Test
    fun `get top stories`(){
        runBlocking {
            val article = ArticleRepo.getTopStoriesArticles()
            assertNotNull(article)
        }
    }
}
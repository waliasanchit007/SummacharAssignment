package `in`.summachar.android.network

import `in`.summachar.android.models.TopHeadlinesResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi{

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey :String = "90700bcf4ef948ebb766ab127d06ff77",
        @Query("country") country:String? = null,
        @Query("category") category:String? = null,
        @Query("language") language:String = "en",
        @Query("pageSize") pageSize:Int = 100
    ):Response<TopHeadlinesResponse>
}


object ApiClient{

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api = retrofitBuilder.create(NewsApi::class.java)

}
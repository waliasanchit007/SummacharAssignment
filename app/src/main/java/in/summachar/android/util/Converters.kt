package `in`.summachar.android.util

import `in`.summachar.android.models.Article
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.ParameterizedType

class Converters {
    private val moshi = Moshi.Builder().build()
    private val listMydData :ParameterizedType = Types.newParameterizedType(List::class.java,Article::class.java)
    private val jsonAdapter: JsonAdapter<List<Article>> = moshi.adapter(listMydData)

//    @TypeConverter
//    fun listMyModelToJsonStr(listMyModel: List<Article>?): String? {
//        return jsonAdapter.toJson(listMyModel)
//    }
//
//    @TypeConverter
//    fun jsonStrToListMyModel(jsonStr: String?): List<Article>? {
//        return jsonStr?.let { jsonAdapter.fromJson(jsonStr) }
//    }

}
package `in`.summachar.android

import `in`.summachar.android.database.getDatabase
import `in`.summachar.android.models.Article
import `in`.summachar.android.network.ApiClient
import `in`.summachar.android.repository.ArticlesRepository
import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivityViewModel(application: Application):AndroidViewModel(application) {


//    private val _article = MutableLiveData<List<Article>>()
//    val article: LiveData<List<Article>> get() = _article
//
//    private val _indianArticles = MutableLiveData<List<Article>>()
//    val indianArticles: LiveData<List<Article>> get() = _indianArticles
//
//    private val _businessArticles = MutableLiveData<List<Article>>()
//    val businessArticles: LiveData<List<Article>> get() = _businessArticles
//
//    private val _entertainmentArticles = MutableLiveData<List<Article>>()
//    val entertainmentArticles: LiveData<List<Article>> get() = _entertainmentArticles
//
//    private val _healthArticles = MutableLiveData<List<Article>>()
//    val healthArticles: LiveData<List<Article>> get() = _healthArticles

    private val articlesRepository = ArticlesRepository(getDatabase(application))
    private lateinit var listReturnedByNetworkCall: List<String?>

    val _articleFetchedFromApi = MutableLiveData<Boolean>(false)
    val articleFetchedFromApi:LiveData<Boolean> get() = _articleFetchedFromApi


    val topStoriesArticles = articlesRepository.top
    val indianArticles = articlesRepository.indian
    val businessArticles = articlesRepository.business
    val entertainmentArticles = articlesRepository.entertainment
    val healthArticles = articlesRepository.health


    fun onRefreshButtonClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            articlesRepository.saveNetworkDataToDataBase(listReturnedByNetworkCall)
        }
    }


    init {
        //network call
        viewModelScope.launch {
            listReturnedByNetworkCall = withContext(Dispatchers.IO) {
                articlesRepository.networkCall()
            }
            //if newly installed, directly add this to database else show refresh Feed Button
            if(articlesRepository.checkIfDatabaseIsEmpty()){
                articlesRepository.saveNetworkDataToDataBase(listReturnedByNetworkCall)
            }else _articleFetchedFromApi.postValue(true)
        }
    }





        /**
         * Factory for constructing DevByteViewModel with parameter
         */
        class Factory(val app: Application) : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return MainActivityViewModel(app) as T
                }
                throw IllegalArgumentException("Unable to construct viewmodel")
            }
        }
}
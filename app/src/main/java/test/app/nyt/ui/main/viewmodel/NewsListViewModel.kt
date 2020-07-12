package test.app.nyt.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import test.app.nyt.data.model.nytdata.NytData
import test.app.nyt.data.model.nytdata.Result
import test.app.nyt.data.repository.MainRepository
import test.app.nyt.utils.NetworkHelper
import test.app.nyt.utils.Resource
import test.app.nyt.utils.Status

class NewsListViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _apiStatus = MutableLiveData<Status>()
    val apiStatus: LiveData<Status>
        get() = _apiStatus

    private val _nytData = MutableLiveData<NytData>()
    val nytData: LiveData<NytData>
        get() = _nytData


    fun fetchNews(section:String,period:String,apiKey:String) {
        viewModelScope.launch {
            _apiStatus.postValue(Status.LOADING)
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getNews(section,period,apiKey).let {
                    if (it.isSuccessful) {
                        _apiStatus.postValue(Status.SUCCESS)
                        _nytData.postValue(it.body())
                    } else {
                        _apiStatus.postValue(Status.ERROR)
                    }
                }
            } else {
                _apiStatus.postValue(Status.ERROR)
            }
        }
    }
}
package test.app.nyt.data.repository

import test.app.nyt.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getNews(section:String,period:String,apiKey:String) =  apiHelper.getNews(section,period,apiKey)

}
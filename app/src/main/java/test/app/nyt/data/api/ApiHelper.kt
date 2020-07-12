package test.app.nyt.data.api

import com.google.gson.JsonObject
import retrofit2.Response
import test.app.nyt.data.model.nytdata.NytData

interface ApiHelper {

    suspend fun getNews(section:String,period:String,apiKey:String): Response<NytData>
}
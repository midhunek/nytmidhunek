package test.app.nyt.data.api

import com.google.gson.JsonObject
import retrofit2.Response
import test.app.nyt.data.model.nytdata.NytData
import test.app.nyt.data.model.nytdata.Result
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getNews(
        section: String,
        period: String,
        apiKey: String
    ): Response<NytData>  = apiService.getNews(section,period,apiKey)

}
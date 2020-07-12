package test.app.nyt.data.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import test.app.nyt.data.model.nytdata.NytData
import test.app.nyt.data.model.nytdata.Result

interface ApiService {

    @GET("mostpopular/v2/mostviewed/{section}/{period}.json")
    suspend fun getNews(@Path("section") section:String,@Path("period") period:String,
        @Query("api-key") apiKey: String): Response<NytData>

}
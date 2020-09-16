package io.github.cloudyhug.cloudycook

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface RemoteDatabaseService {
  @GET("/recipes")
  suspend fun getRecipes(): Response<ResponseBody>
}
package id.ac.ui.cs.mobileprogramming.roshaniayu.helloworld

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://248dc904e76cbc9b13c26a7958acd394.m.pipedream.net"
    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val RETROFIT_SERVICE: ApiService = client.create(ApiService::class.java)
}
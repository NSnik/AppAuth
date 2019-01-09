package net.openid.appauthdemo.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class NetworkClient {

    private val apiService: ApiService

    init {
        val baseUrl = "https://api.is74.ru"
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun domofons(bearerToken: String): Observable<List<IntercomModel>> {
        return apiService.domofons(bearerToken)
    }

    fun openDomofon(id: Long, bearerToken: String): Observable<OpenIntercomStatus> {
        return apiService.openIntercom(id, bearerToken)
    }

}
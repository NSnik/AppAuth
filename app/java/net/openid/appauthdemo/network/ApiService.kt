package net.openid.appauthdemo.network

import retrofit2.http.*
import rx.Observable

interface ApiService {

    @Headers(
            "Content-Type: application/json;charset=utf-8",
            "User-Agent: student-app"
    )
    @GET("/domofon/relays")
    fun domofons(
            @Header("Authorization") bearerToken: String,
            @Query("pagesize") pageSize: Int = 20,
            @Query("pagination") pagination: Int = 1
    ): Observable<List<IntercomModel>>


    @Headers(
            "Content-Type: application/json;charset=utf-8",
            "User-Agent: student-app"
    )
    @POST("/domofon/relays/{id}/open")
    fun openIntercom(
            @Path("id") id: Long,
            @Header("Authorization") bearerToken: String
    ): Observable<OpenIntercomStatus>

}
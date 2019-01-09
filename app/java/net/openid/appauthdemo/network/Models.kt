package net.openid.appauthdemo.network

import com.google.gson.annotations.SerializedName

data class IntercomModel(
        @SerializedName("ADDRESS") val address: String,
        @SerializedName("RELAY_ID") val id: Long
)

data class OpenIntercomStatus(
        @SerializedName("statusCode") val statusCode: Int
)
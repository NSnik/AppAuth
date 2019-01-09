package net.openid.appauthdemo.network

import com.auth0.android.jwt.JWT
import com.example.demofon.AppState
import rx.Observable

class IntercomInteractor(
        private val appState: AppState,
        private val networkClient: NetworkClient) {

    fun intercoms(): Observable<List<IntercomModel>> {
        return networkClient.domofons(getBearerToken())
    }

    fun openIntercom(id: Long): Observable<OpenIntercomStatus> {
        return networkClient.openDomofon(id, getBearerToken())
    }

    private fun getBearerToken(): String {
        val token = appState.getToken() ?: ""
        val jwt = JWT(token)
        return "Bearer ${jwt.id}"
    }

}
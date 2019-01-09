package net.openid.appauthdemo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.demofon.AppState
import net.openid.appauthdemo.R

class MainActivity : AppCompatActivity() {

    private lateinit var appState: AppState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appState = AppState(this)
    }

    override fun onResume() {
        super.onResume()

        if (appState.getToken() == null) {
            startActivity(LoginActivity.newIntent(this))

        } else {
            startActivity(IntercomListActivity.newIntent(this))
        }

    }

}

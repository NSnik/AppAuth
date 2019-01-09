package net.openid.appauthdemo.ui

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.demofon.AppState
import net.openid.appauthdemo.R
import net.openid.appauthdemo.network.IntercomInteractor
import net.openid.appauthdemo.network.IntercomModel
import net.openid.appauthdemo.network.NetworkClient
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class IntercomListActivity : AppCompatActivity() {

    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView

    private lateinit var progressDialog: ProgressDialog

    private var items = listOf<IntercomModel>()
    private lateinit var intercomInteractor: IntercomInteractor
    private lateinit var appState: AppState

    companion object {

        fun newIntent(context: Context) = context.intentFor<IntercomListActivity>()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intercom_list_activity)

        ButterKnife.bind(this)

        val recyclerAdapter = IntercomRecyclerAdapter(
                layoutInflater
        ) { position -> onIntercomClick(items[position]) }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        progressDialog = ProgressDialog(this)
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)

        val networkClient = NetworkClient()

        appState = AppState(this)
        intercomInteractor = IntercomInteractor(appState, networkClient)

        progressDialog.setMessage("Загрузка данных...")
        progressDialog.show()

        intercomInteractor.intercoms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ items ->
                    progressDialog.dismiss()

                    this.items = items

                    recyclerAdapter.items = items
                    recyclerAdapter.notifyDataSetChanged()

                }, {
                    it.printStackTrace()
                    progressDialog.dismiss()

                    toast("Ошибка зашрузки данных")
                })
    }

    @OnClick(R.id.logoutButton)
    fun onLogoutClick() {
        appState.logout()
        finish()
    }

    private fun onIntercomClick(intercomModel: IntercomModel) {
        progressDialog.setMessage("Открытие фомофона...")
        progressDialog.show()

        intercomInteractor.openIntercom(intercomModel.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    progressDialog.dismiss()
                    toast("Успех")

                }, {
                    it.printStackTrace()
                    progressDialog.dismiss()

                    toast("Ошибка")
                })
    }

}
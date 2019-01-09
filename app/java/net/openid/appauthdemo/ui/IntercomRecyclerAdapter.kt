package net.openid.appauthdemo.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import net.openid.appauthdemo.R
import net.openid.appauthdemo.network.IntercomModel

class IntercomRecyclerAdapter(
        private val layoutInflater: LayoutInflater,
        private val onOpenButtonClick: (position: Int) -> Unit
) : RecyclerView.Adapter<IntercomRecyclerAdapter.ViewHolder>() {

    var items = listOf<IntercomModel>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(R.layout.intercom_recycler_item, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val intercomModel = items[position]
        viewHolder.textView.text = intercomModel.address
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.textView) lateinit var textView: TextView

        init {
            ButterKnife.bind(this, itemView)
        }


        @OnClick(R.id.openButton)
        fun onOpenButtonClick() {
            onOpenButtonClick.invoke(adapterPosition)
        }

    }

}
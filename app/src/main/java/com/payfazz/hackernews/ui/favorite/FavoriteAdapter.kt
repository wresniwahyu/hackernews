package com.payfazz.hackernews.ui.favorite

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.payfazz.hackernews.R
import com.payfazz.hackernews.data.Favorites
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteAdapter(private val context: Context, private val items: MutableList<Favorites>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: Favorites) {
            itemView.also {
                item.apply {
                    it.tv_title.text = title

                }
            }

        }
    }

}
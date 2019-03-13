package com.payfazz.hackernews.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.payfazz.hackernews.R
import com.payfazz.hackernews.data.DBStores
import com.payfazz.hackernews.data.News
import com.payfazz.hackernews.utils.Constant.FORMAT_DATE
import com.payfazz.hackernews.utils.toReadableDate
import kotlinx.android.synthetic.main.item_main.view.*
import org.jetbrains.anko.toast

class MainAdapter(private val context: Context, private val items: MutableList<News>, private val db: DBStores) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var isFavorite = false

        fun bindItem(item: News) {
            isFavorite = db.isFavoriteItem(item.itemId)

            itemView.also {
                item.apply {

                    it.tv_id.text = context.getString(R.string.format_id, itemId)
                    it.tv_title.text = title
                    it.tv_author.text = context.getString(R.string.format_author, by)
                    it.tv_date.text = time.toReadableDate(FORMAT_DATE)

                    it.img_favorite.setOnClickListener { saveFavoriteItem(item) }

                }
            }

            setSavedStatus()
        }

        private fun saveFavoriteItem(data: News) {
            data.apply {
                if (!isFavorite) {
                    db.insertFavoriteItem(itemId, title)

                    context.apply { toast(getString(R.string.favorite_added)) }
                    isFavorite = !isFavorite
                } else {
                    db.deleteFavoriteItem(itemId)
                    context.apply { toast(getString(R.string.favorite_removed)) }
                    isFavorite = !isFavorite
                }

                setSavedStatus()

            }
        }

        private fun setSavedStatus() {
            val drawable = if (isFavorite) R.drawable.ic_favorite_active else R.drawable.ic_favorite
            Glide.with(context).load(drawable).into(itemView.img_favorite)
        }

    }

}
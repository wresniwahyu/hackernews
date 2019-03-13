package com.payfazz.hackernews.ui.favorite

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.payfazz.hackernews.R
import com.payfazz.hackernews.data.DBStores
import com.payfazz.hackernews.data.Favorites
import com.payfazz.hackernews.data.News
import com.payfazz.hackernews.utils.database
import com.payfazz.hackernews.utils.gone
import com.payfazz.hackernews.utils.visible
import kotlinx.android.synthetic.main.activity_favorite.*
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteActivity : AppCompatActivity() {

    private lateinit var adapter: FavoriteAdapter
    private lateinit var db: DBStores

    private var items: MutableList<Favorites> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        db = DBStores(this)
        adapter = FavoriteAdapter(this, items)

        rv_favorite.adapter = adapter

        showFavorite()

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showFavorite() {
        progress_bar.visible()
        database.use {
            val result = select(Favorites.TABLE_FAVORITE).orderBy(Favorites.TITLE, SqlOrderDirection.ASC)
            val favorites = result.parseList(classParser<Favorites>())

            items.clear()
            items.addAll(favorites)

            progress_bar.gone()
            adapter.notifyDataSetChanged()
        }
    }

}

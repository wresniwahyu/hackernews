package com.payfazz.hackernews.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.payfazz.hackernews.R
import com.payfazz.hackernews.data.DBStores
import com.payfazz.hackernews.data.News
import com.payfazz.hackernews.network.model.item.ItemResponse
import com.payfazz.hackernews.ui.favorite.FavoriteActivity
import com.payfazz.hackernews.utils.database
import com.payfazz.hackernews.utils.gone
import com.payfazz.hackernews.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var adapter: MainAdapter
    private lateinit var db: DBStores
    private lateinit var menuItem: Menu

    private var items: MutableList<News> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBStores(this)
        adapter = MainAdapter(this, items, db)

        rv_item.adapter = adapter

        presenter.doLoadTopStories()
    }

    private fun showItem() {
        showLoading()
        database.use {
            val result = select(News.TABLE_NEWS).orderBy(News.TIME, SqlOrderDirection.DESC)
            val newsItem = result.parseList(classParser<News>())

            items.clear()
            items.addAll(newsItem)

            hideLoading()
            adapter.notifyDataSetChanged()
            checkSize()
        }
    }

    fun checkSize() {
//        layout_no_saved.visibility = if (items.size == 0) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        menuItem = menu
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity<FavoriteActivity>()
        return super.onOptionsItemSelected(item)
    }

    override var presenter: MainContract.Presenter = MainPresenter(this)

    override fun showSuccess(response: ArrayList<Int>) {
        response.forEach { presenter.doLoadItem(it) }
    }

    override fun showSuccessItem(response: ItemResponse) {
        response.apply {
            if (!db.isItemExist(id)) {
                db.insertNewsItem(id, title, by, time)
            }

        }

        showItem()
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.gone()
    }

    override fun showError(message: String) {

    }
}

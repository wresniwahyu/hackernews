package com.payfazz.hackernews.data

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.payfazz.hackernews.utils.database
import com.payfazz.hackernews.utils.toast
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.warn

class DBStores(private val context: Context) {

    private val log = AnkoLogger(this.javaClass)

    fun insertNewsItem(
        itemId: Int,
        title: String,
        by: String,
        time: Long
    ) {

        try {
            News.apply {
                context.database.use {
                    insert(
                        TABLE_NEWS,
                        ITEM_ID to itemId,
                        TITLE to title,
                        BY to by,
                        TIME to time
                    )
                }
            }

        } catch (e: SQLiteConstraintException) {
            context.toast(e.localizedMessage)
        }

    }

    fun deleteItem(itemId: Int) {
        try {
            context.database.use { delete(News.TABLE_NEWS, "(${News.ITEM_ID} = {id})", "id" to itemId) }
        } catch (e: SQLiteConstraintException) {
            context.toast(e.localizedMessage)
        }

    }

    fun isItemExist(itemId: Int): Boolean {
        var isExist = false
        context.database.use {
            val result = select(News.TABLE_NEWS)
                .whereArgs("(${News.ITEM_ID} = {id})",
                    "id" to itemId)
            val exist = result.parseList(classParser<News>())

            log.warn { "item $itemId is exist ->  $exist" }

            if (!exist.isEmpty()) {
                isExist = true
            }
        }
        return isExist
    }

    fun insertFavoriteItem(itemId: Int, title: String) {

        try {
            Favorites.apply {
                context.database.use {
                    insert(TABLE_FAVORITE, ITEM_ID to itemId, TITLE to title)
                }
            }

        } catch (e: SQLiteConstraintException) {
            context.toast(e.localizedMessage)
        }

    }

    fun deleteFavoriteItem(itemId: Int) {
        try {
            context.database.use { delete(Favorites.TABLE_FAVORITE, "(${Favorites.ITEM_ID} = {id})", "id" to itemId) }
        } catch (e: SQLiteConstraintException) {
            context.toast(e.localizedMessage)
        }

    }

    fun isFavoriteItem(itemId: Int): Boolean {
        var isFavorite = false
        context.database.use {
            val result = select(Favorites.TABLE_FAVORITE)
                .whereArgs("(${Favorites.ITEM_ID} = {id})",
                    "id" to itemId)
            val favorite = result.parseList(classParser<Favorites>())

            log.warn { "item $itemId is favorite ->  $favorite" }

            if (!favorite.isEmpty()) {
                isFavorite = true
            }
        }
        return isFavorite
    }

}
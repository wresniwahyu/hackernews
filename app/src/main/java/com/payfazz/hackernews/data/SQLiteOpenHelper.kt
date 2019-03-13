package com.payfazz.hackernews.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.payfazz.hackernews.data.News.Companion.DB_NEWS
import org.jetbrains.anko.db.*

class SQLiteOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DB_NEWS, null, 1) {

    companion object {
        private var instance: SQLiteOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): SQLiteOpenHelper {
            if (instance == null) {
                instance = SQLiteOpenHelper(ctx.applicationContext)
            }
            return instance as SQLiteOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        News.apply {
            db.createTable(
                TABLE_NEWS, true,
                ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ITEM_ID to INTEGER + UNIQUE,
                TITLE to TEXT,
                BY to TEXT,
                TIME to INTEGER
            )
        }

        Favorites.apply {
            db.createTable(
                TABLE_FAVORITE, true,
                ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ITEM_ID to INTEGER + UNIQUE,
                TITLE to TEXT
            )
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {
        db.dropTable(News.DB_NEWS, true)
    }

}
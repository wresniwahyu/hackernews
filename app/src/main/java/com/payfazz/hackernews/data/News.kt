package com.payfazz.hackernews.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val id: Long,
    val itemId: Int,
    val title: String,
    val by: String,
    val time: Long
) : Parcelable {

    companion object {
        const val DB_NEWS = "news.db"
        const val TABLE_NEWS = "TABLE_NEWS"

        const val ID = "ID_"
        const val ITEM_ID = "ITEM_ID"
        const val TITLE = "TITLE"
        const val BY = "BY"
        const val TIME = "TIME"
    }

}
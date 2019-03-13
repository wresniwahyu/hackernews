package com.payfazz.hackernews.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorites(
    val id: Long,
    val itemId: Long,
    val title: String
) : Parcelable {

    companion object {
        const val TABLE_FAVORITE = "TABLE_FAVORITE"

        const val ID = "ID_"
        const val ITEM_ID = "ITEM_ID"
        const val TITLE = "TITLE"
    }

}
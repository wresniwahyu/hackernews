package com.payfazz.hackernews.network.model.item

import com.google.gson.annotations.SerializedName

data class ItemResponse(

	@field:SerializedName("score")
	val score: Int,

	@field:SerializedName("by")
	val by: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("time")
	val time: Long,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("descendants")
	val descendants: Int,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("kids")
	val kids: List<Int>
)
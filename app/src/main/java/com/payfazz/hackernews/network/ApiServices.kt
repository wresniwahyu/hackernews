package com.payfazz.hackernews.network

import com.payfazz.hackernews.network.model.item.ItemResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("topstories.json")
    fun getTopStories(): Observable<ArrayList<Int>>

    @GET("item/{id}.json")
    fun getItemDetail(@Path("id") id: Int): Observable<ItemResponse>

}
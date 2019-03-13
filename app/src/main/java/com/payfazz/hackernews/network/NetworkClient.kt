package com.payfazz.hackernews.network

import com.payfazz.hackernews.BuildConfig
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class NetworkClient {

    companion object {

        val createService by lazy {
            NetworkClient.create()
        }
        var disposable: Disposable? = null

        fun create(): ApiServices {
            val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val builder = OkHttpClient.Builder()

            if (!builder.interceptors().contains(logging))
                builder.addInterceptor(logging)

            val okHttpClient = builder.build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .build()

            return retrofit.create(ApiServices::class.java)
        }
    }

}
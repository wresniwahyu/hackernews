package com.payfazz.hackernews.base

interface BaseView<T> {

    var presenter: T

    fun showLoading()

    fun hideLoading()

    fun showError(message: String)

}
package com.payfazz.hackernews.ui.main

import com.payfazz.hackernews.base.BasePresenter
import com.payfazz.hackernews.base.BaseView
import com.payfazz.hackernews.network.model.item.ItemResponse

interface MainContract {

    interface View : BaseView<Presenter> {

        fun showSuccess(response: ArrayList<Int>)

        fun showSuccessItem(response: ItemResponse)

    }

    interface Presenter : BasePresenter {

        fun doLoadTopStories()

        fun doLoadItem(id: Int)

    }

}
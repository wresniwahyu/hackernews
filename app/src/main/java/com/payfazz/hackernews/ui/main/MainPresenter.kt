package com.payfazz.hackernews.ui.main

import com.payfazz.hackernews.network.NetworkClient.Companion.createService
import com.payfazz.hackernews.network.NetworkClient.Companion.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    init {
        view.presenter = this
    }

    override fun doLoadTopStories() {
        view.showLoading()
        disposable = createService.getTopStories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    view.showSuccess(result)
                },
                {error ->
                    view.hideLoading()
                    view.showError(error.toString())
                }
            )
    }

    override fun doLoadItem(id: Int) {
        view.showLoading()
        disposable = createService.getItemDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    view.hideLoading()
                    view.showSuccessItem(result)
                },
                {error ->
                    view.hideLoading()
                    view.showError(error.toString())
                }
            )
    }

    override fun onDestroy() {
        disposable?.dispose()
    }
}
package com.example.githubissuessearch.ui.issue

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.githubissuessearch.base.BaseViewModel
import com.example.githubissuessearch.network.GithubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IssueListViewModel : BaseViewModel() {

    @Inject
    lateinit var postApi: GithubApi

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    init{
        loadPosts()
    }

    private fun loadPosts(){
        subscription = postApi.getIssues()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { onRetrievePostListSuccess() },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(){

    }

    private fun onRetrievePostListError(){

    }
}
package com.example.githubissuessearch.ui.issue

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

    }

    private fun onRetrievePostListFinish(){

    }

    private fun onRetrievePostListSuccess(){

    }

    private fun onRetrievePostListError(){

    }
}
package com.example.githubissuessearch.ui.issue

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.githubissuessearch.base.BaseViewModel
import com.example.githubissuessearch.model.Issue
import com.example.githubissuessearch.network.GithubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IssueListViewModel : BaseViewModel() {

    @Inject
    lateinit var githubApi: GithubApi

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val textInformation: MutableLiveData<Int> = MutableLiveData()
    val issuesAdapter: IssuesAdapter = IssuesAdapter()
    var query: String = ""

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun loadPosts(){
        subscription = githubApi.getIssues(makeIssuesByRepoSort(query)) //"repo:olivierlacan/keep-a-changelog")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result.items) },
                { err -> onRetrievePostListError(err) }
            )
    }

    private fun onRetrievePostListStart(){
        textInformation.value = View.GONE
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(issues: List<Issue>) {
        issuesAdapter.updatePostList(issues)
    }

    private fun onRetrievePostListError(err: Throwable) {
        var err1 = err
    }

    private fun makeIssuesByRepoSort(repository: String): String{
        return QUERY_REPO_SORT + repository
    }
}
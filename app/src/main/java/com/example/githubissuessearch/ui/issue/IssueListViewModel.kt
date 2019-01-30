package com.example.githubissuessearch.ui.issue

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.githubissuessearch.R
import com.example.githubissuessearch.base.BaseViewModel
import com.example.githubissuessearch.model.Issue
import com.example.githubissuessearch.network.GithubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class IssueListViewModel : BaseViewModel() {

    @Inject
    lateinit var githubApi: GithubApi

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val logo: MutableLiveData<Int> = MutableLiveData()
    val issuesAdapter: IssuesAdapter = IssuesAdapter()
    var query: String = ""
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

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
        errorMessage.value = null
        logo.value = View.GONE
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(issues: List<Issue>) {
        issuesAdapter.updatePostList(issues)
    }

    private fun onRetrievePostListError(error: Throwable) {
        logo.value = View.VISIBLE
        errorMessage.value = R.string.issues_error
    }

    private fun makeIssuesByRepoSort(repository: String): String{
        return QUERY_REPO_SORT + repository
    }
}
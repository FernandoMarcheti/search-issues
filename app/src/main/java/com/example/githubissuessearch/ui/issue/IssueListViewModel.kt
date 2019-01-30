package com.example.githubissuessearch.ui.issue

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.githubissuessearch.R
import com.example.githubissuessearch.base.BaseResponse
import com.example.githubissuessearch.base.BaseViewModel
import com.example.githubissuessearch.model.IssueResponse
import com.example.githubissuessearch.network.GithubApi
import javax.inject.Inject

class IssueListViewModel : BaseViewModel() {

    @Inject
    lateinit var githubApi: GithubApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val logo: MutableLiveData<Int> = MutableLiveData()
    val issuesAdapter: IssuesAdapter = IssuesAdapter()
    var query: String = ""
    val errorClickListener = View.OnClickListener { loadPosts() }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun loadPosts(){
        callApi(githubApi.getIssues(makeIssuesByRepoSort(query)), R.string.issues_error)
    }

    override fun onRetrievePostListStart() {
        logo.value = View.GONE
        loadingVisibility.value = View.VISIBLE
    }

    override fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    override fun onRetrievePostListSuccess(response: BaseResponse) {
        val issuesResponse: IssueResponse = response as IssueResponse
        val sortedList = issuesResponse.items.sortedWith(compareBy { it.number })
        issuesAdapter.updatePostList(sortedList)
    }

    override fun onRetrievePostListError(err: Throwable?, optionalMessage: Int?) {
        super.onRetrievePostListError(err, optionalMessage)
        logo.value = View.VISIBLE
    }

    private fun makeIssuesByRepoSort(repository: String): String{
        return QUERY_REPO_SORT + repository
    }
}
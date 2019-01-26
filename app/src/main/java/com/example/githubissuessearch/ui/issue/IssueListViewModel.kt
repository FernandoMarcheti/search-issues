package com.example.githubissuessearch.ui.issue

import com.example.githubissuessearch.base.BaseViewModel
import com.example.githubissuessearch.network.GithubApi
import javax.inject.Inject

class IssueListViewModel : BaseViewModel() {

    @Inject
    lateinit var postApi: GithubApi

}
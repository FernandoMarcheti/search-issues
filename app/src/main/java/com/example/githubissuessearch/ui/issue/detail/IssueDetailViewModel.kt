package com.example.githubissuessearch.ui.issue.detail

import android.arch.lifecycle.MutableLiveData
import com.example.githubissuessearch.base.BaseViewModel
import com.example.githubissuessearch.model.Issue

class IssueDetailViewModel : BaseViewModel() {

    private val number = MutableLiveData<Int>()
    private val title = MutableLiveData<String>()
    private val body = MutableLiveData<String>()

    fun bind(issue: Issue){
        number.value = issue.number
        title.value = issue.title
        body.value = issue.body
    }

    fun getNumber(): MutableLiveData<Int> = number

    fun getTitle(): MutableLiveData<String> = title

    fun getBody(): MutableLiveData<String> = body

}
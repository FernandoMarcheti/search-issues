package com.example.githubissuessearch.ui.issue

import android.arch.lifecycle.MutableLiveData
import com.example.githubissuessearch.model.Issue

class IssueViewModel{

    private val number = MutableLiveData<Int>()
    private val title = MutableLiveData<String>()

    fun bind(issue: Issue){
        number.value = issue.number
        title.value = issue.title
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getNumber(): MutableLiveData<Int> {
        return number
    }
}
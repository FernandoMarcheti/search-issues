package com.example.githubissuessearch.ui.issue.detail

import android.arch.lifecycle.MutableLiveData
import android.widget.ImageView
import com.example.githubissuessearch.base.BaseViewModel
import com.example.githubissuessearch.model.Issue
import com.example.githubissuessearch.utils.RoundedTransformation
import com.squareup.picasso.Picasso

class IssueDetailViewModel : BaseViewModel() {

    private val number = MutableLiveData<Int>()
    private val title = MutableLiveData<String>()
    private val body = MutableLiveData<String>()

    fun bind(issue: Issue, imageView: ImageView){
        Picasso.get().load(issue.user.urlAvatar).into(imageView);
        number.value = issue.number
        title.value = issue.title
        body.value = issue.body
    }

    fun getNumber(): MutableLiveData<Int> = number

    fun getTitle(): MutableLiveData<String> = title

    fun getBody(): MutableLiveData<String> = body

}
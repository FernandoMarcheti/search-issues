package com.example.githubissuessearch.ui.issue

import android.arch.lifecycle.MutableLiveData
import android.widget.ImageView
import com.example.githubissuessearch.model.Issue
import com.example.githubissuessearch.utils.RoundedTransformation
import com.squareup.picasso.Picasso


class IssueViewModel{

    private val number = MutableLiveData<Int>()
    private val title = MutableLiveData<String>()

    fun bind(
        issue: Issue,
        imageView: ImageView
    ){
        val transformation = RoundedTransformation(250f, 0f)
        Picasso.get().load(issue.user.urlAvatar).transform(transformation).into(imageView);

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
package com.example.githubissuessearch.network

import com.example.githubissuessearch.model.Issue
import io.reactivex.Observable
import retrofit2.http.GET

interface GithubApi {

    @GET("/search/issues")
    fun getIssues(): Observable<List<Issue>>
}
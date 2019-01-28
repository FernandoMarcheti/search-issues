package com.example.githubissuessearch.network

import com.example.githubissuessearch.model.Issue
import com.example.githubissuessearch.model.IssueResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("/search/issues")
    fun getIssues( @Query("q", encoded = true) repository: String): Observable<IssueResponse>
}
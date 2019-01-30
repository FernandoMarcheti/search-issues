package com.example.githubissuessearch.model

import com.example.githubissuessearch.base.BaseResponse

data class IssueResponse(val items: List<Issue>): BaseResponse

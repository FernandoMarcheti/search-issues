package com.example.githubissuessearch.model

import java.io.Serializable

data class Issue(val id: Int, val number: Int, val title: String, val body: String) : Serializable
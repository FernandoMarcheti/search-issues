package com.example.githubissuessearch.base

import android.arch.lifecycle.ViewModel
import com.example.githubissuessearch.injection.DaggerViewModelInjector
import com.example.githubissuessearch.injection.NetworkModule
import com.example.githubissuessearch.injection.ViewModelInjector
import com.example.githubissuessearch.ui.issue.IssueListViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is IssueListViewModel -> injector.inject(this)
        }
    }
}
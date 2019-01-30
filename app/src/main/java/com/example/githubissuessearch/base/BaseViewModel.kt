package com.example.githubissuessearch.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.StringRes
import com.example.githubissuessearch.R
import com.example.githubissuessearch.injection.DaggerViewModelInjector
import com.example.githubissuessearch.injection.NetworkModule
import com.example.githubissuessearch.injection.ViewModelInjector
import com.example.githubissuessearch.ui.issue.IssueListViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    protected lateinit var subscription: Disposable
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

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

    protected fun <T : BaseResponse> callApi(observable: Observable<T>, optionalMessage: Int? = 0) {
        subscription = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { err -> onRetrievePostListError(err, optionalMessage) }
            )
    }

    open fun onRetrievePostListStart() {
        errorMessage.value = null
    }

    open fun onRetrievePostListFinish() {}

    open fun onRetrievePostListSuccess(response: BaseResponse){ }

    open fun onRetrievePostListError(err: Throwable?, optionalMessage: Int?){
        when(err){
            is HttpException -> httpException(err, optionalMessage)
            is UnknownHostException -> unknownHostException()
            else -> displayGenericError()
        }
    }

    private fun displayGenericError() {
        errorMessage.value = R.string.generic_error
    }

    private fun unknownHostException() {
        errorMessage.value = R.string.no_network_error
    }

    private fun httpException(err: HttpException, optionalMessage: Int?) {
        if(err.code().toString().startsWith("4")){
            if(optionalMessage.toString().isNotBlank()){
                errorMessage.value = optionalMessage
            } else {
                errorMessage.value = R.string.generic_error
            }
        }
    }
}
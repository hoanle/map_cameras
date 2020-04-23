package com.example.mycamera.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

open class BaseViewModel : ViewModel() {
    protected val throwableLiveData: MutableLiveData<Throwable> =
        MutableLiveData<Throwable>()

    val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun dispose() {
        compositeDisposable.clear()
    }

    fun getSchedulerIO(): Scheduler {
        return Schedulers.io()
    }

    fun getMainScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
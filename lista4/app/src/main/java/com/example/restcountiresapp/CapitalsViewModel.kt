package com.example.restcountiresapp

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CapitalsViewModel: ViewModel() {
    private var callback: ((List<RecyclerViewItems>) -> Unit) ?= null

    fun setOnDataUptatedCallback(c: ((List<RecyclerViewItems>) -> Unit)) {
        callback = c
    }

    fun updateCapitals() {
        ApiClient.apiService.getCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback?.invoke(it)
            },
                {
                    it.printStackTrace()
                })
    }
}
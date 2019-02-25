package dev.msemyak.coroutineslab.ui.main

import dev.msemyak.coroutineslab.data.ApiService
import dev.msemyak.coroutineslab.data.model.Country
import kotlinx.coroutines.*

class MainPresenter(private val networkService: ApiService) : MainContract.Presenter {

    private var view: MainContract.View? = null

    private val uiJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + uiJob)

    override fun loadData() {

        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            view?.showToast(exception.message ?: "Exception happened")
        }

        uiScope.launch(exceptionHandler) {
            val countriesList = withContext(Dispatchers.IO) { getDataFromServerAsync() }
            view?.showData(countriesList)
        }

    }

    private suspend fun getDataFromServerAsync(): List<Country> {
        return networkService.getAllCountriesAsync().await()
    }

    fun attachView(view: MainContract.View) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

}
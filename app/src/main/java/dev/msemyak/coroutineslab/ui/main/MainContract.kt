package dev.msemyak.coroutineslab.ui.main

import dev.msemyak.coroutineslab.data.model.Country

interface MainContract {

    interface View {
        fun showData(countries: List<Country>)
        fun showToast(message: String)
    }

    interface Presenter {
        fun loadData()
    }
}
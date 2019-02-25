package dev.msemyak.coroutineslab.data

import dev.msemyak.coroutineslab.data.model.Country
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("all")
    fun getAllCountriesAsync(): Deferred<List<Country>>

}
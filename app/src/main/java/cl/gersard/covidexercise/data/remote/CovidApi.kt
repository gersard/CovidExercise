package cl.gersard.covidexercise.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {

    @GET(ApiConstants.METHOD_TOTAL)
    suspend fun totalCovidCases(@Path("date") date: String)

}
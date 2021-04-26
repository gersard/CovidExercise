package cl.gersard.covidexercise.data.remote

import cl.gersard.covidexercise.data.remote.model.CovidResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {

    @GET(ApiConstants.METHOD_TOTAL)
    suspend fun totalCovidCases(@Path("date") date: String): Response<CovidResponse>

}
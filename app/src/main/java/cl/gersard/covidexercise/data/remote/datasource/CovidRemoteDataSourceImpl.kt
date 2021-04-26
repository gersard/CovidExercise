package cl.gersard.covidexercise.data.remote.datasource

import cl.gersard.covidexercise.data.remote.CovidApi
import cl.gersard.covidexercise.data.remote.model.CovidData
import cl.gersard.covidexercise.data.remote.model.CovidResponse
import javax.inject.Inject

class CovidRemoteDataSourceImpl @Inject constructor(private val covidApi: CovidApi) :
    CovidRemoteDataSource {

    override suspend fun getTotalCovidCases(date: String): CovidResponse {
        val response = covidApi.totalCovidCases(date)
        return if (response.isSuccessful) {
            CovidResponse(response.body()?.data, null)
        } else {
            CovidResponse(null, response.body()?.error)
        }
    }
}
package cl.gersard.covidexercise.data.remote.datasource

import cl.gersard.covidexercise.data.remote.model.CovidData
import cl.gersard.covidexercise.data.remote.model.CovidResponse

interface CovidRemoteDataSource {

    suspend fun getTotalCovidCases(date: String): CovidResponse

}
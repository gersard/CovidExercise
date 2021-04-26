package cl.gersard.covidexercise.data.repository

import cl.gersard.covidexercise.domain.CovidResults

interface CovidRepository {

    suspend fun getCovidCases(date: String): CovidResults?

}
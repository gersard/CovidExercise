package cl.gersard.covidexercise.data.repository

import cl.gersard.covidexercise.data.remote.model.CovidData
import cl.gersard.covidexercise.domain.CovidResults

class CovidResultsMapper {

    fun map(covidData: CovidData, dateSelected: String) = CovidResults(
        dateSelected,
        covidData.confirmed,
        covidData.deaths
    )

}
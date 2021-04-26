package cl.gersard.covidexercise.data.repository

import cl.gersard.covidexercise.core.GlobalConstants
import cl.gersard.covidexercise.data.remote.model.CovidData
import cl.gersard.covidexercise.domain.CovidResults
import cl.gersard.covidexercise.ext.format
import java.time.LocalDate

class CovidResultsMapper {

    fun map(covidData: CovidData, dateSelected: String) = CovidResults(
        LocalDate.parse(dateSelected).format(GlobalConstants.DATE_FORMAT_UI),
        covidData.confirmed,
        covidData.deaths
    )

}
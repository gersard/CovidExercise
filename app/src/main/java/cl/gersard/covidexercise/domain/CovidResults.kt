package cl.gersard.covidexercise.domain

import java.time.LocalDate

data class CovidResults(
    val date: String,
    val confirmedCases: Int,
    val deathCases: Int
)
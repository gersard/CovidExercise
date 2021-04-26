package cl.gersard.covidexercise.data.remote

object ApiConstants {

    private const val URL_PATH_TOTAL = "/total"
    private const val URL_PATH_REPORTS = "/reports"
    const val URL_BASE = "https://covid-19-statistics.p.rapidapi.com"

    const val METHOD_TOTAL = URL_BASE + URL_PATH_REPORTS + URL_PATH_TOTAL

}
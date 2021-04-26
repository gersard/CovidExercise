package cl.gersard.covidexercise.core

import androidx.annotation.StringRes

sealed class GenericState<out T> {
    data class Success<out T>(val data: T) : GenericState<T>()
    data class Error(@StringRes var error: Int) : GenericState<Nothing>()
    data class Loading(var loading: Boolean) : GenericState<Nothing>()

}
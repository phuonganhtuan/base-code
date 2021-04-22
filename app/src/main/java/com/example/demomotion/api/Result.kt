package com.example.demomotion.api

data class ResultPet<out T>(val status: CoroutineState, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T) =
            ResultPet(status = CoroutineState.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String) =
            ResultPet(status = CoroutineState.ERROR, data = data, message = message)

        fun <T> loading(data: T?) =
            ResultPet(status = CoroutineState.LOADING, data = data, message = null)
    }
}

enum class CoroutineState { SUCCESS, ERROR, LOADING }

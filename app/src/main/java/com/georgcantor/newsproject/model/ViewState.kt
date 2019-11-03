package com.georgcantor.newsproject.model

sealed class ViewState<ResultType> {

    companion object {
        fun <ResultType> success(data: ResultType): ViewState<ResultType> = Success(data)

        fun <ResultType> loading(): ViewState<ResultType> = Loading()

        fun <ResultType> error(message: String): ViewState<ResultType> = Error(message)
    }

    data class Success<ResultType>(val data: ResultType) : ViewState<ResultType>()

    class Loading<ResultType> : ViewState<ResultType>() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            return true
        }

        override fun hashCode(): Int = javaClass.hashCode()
    }

    data class Error<ResultType>(val message: String = "") : ViewState<ResultType>()

}
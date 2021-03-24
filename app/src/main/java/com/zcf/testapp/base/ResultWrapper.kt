package com.zcf.testapp.base

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val errorCode: Int, val description: String): ResultWrapper<Nothing>()
}

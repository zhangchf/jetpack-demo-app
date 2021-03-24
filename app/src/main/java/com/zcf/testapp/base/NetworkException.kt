package com.zcf.testapp.base

data class NetworkException(
    val code: Int,
    val description: String
) : Throwable()

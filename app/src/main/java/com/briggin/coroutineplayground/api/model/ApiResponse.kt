package com.briggin.coroutineplayground.api.model

sealed class ApiResponse<T>

data class ApiSuccess<T>(val items: List<T>) : ApiResponse<T>()

class ApiError<T> : ApiResponse<T>()
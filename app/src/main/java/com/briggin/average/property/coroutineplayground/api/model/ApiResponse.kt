package com.briggin.average.property.coroutineplayground.api.model

sealed class ApiResponse<T>

data class ApiSuccess<T>(val items: List<T>) : ApiResponse<T>()

class ApiError<T> : ApiResponse<T>()
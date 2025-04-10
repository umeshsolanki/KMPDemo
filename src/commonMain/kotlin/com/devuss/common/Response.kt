package com.devuss.common

//import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val data: T? = null,
    val msg: String? = "Success",
    val success: Boolean = true,
    val code: Int = 200//HttpStatusCode.OK.value
)


@Serializable
open class State<T>

class Loading<T>(val data: T?) : State<T>()
class Error<T>(val data: T?) : State<T>()
class Success<T>(val data: T?) : State<T>()

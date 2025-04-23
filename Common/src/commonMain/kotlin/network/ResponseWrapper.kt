package network

import io.ktor.http.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
open class ResponseWrapper<T>(
    val payload: T? = null, val success: Boolean = true, open var msg: String? = "Success!"
)

@Serializable
class ErrorResponseWrapper<T>(
    val msg: String = "Error, Something went wrong!",
    @Contextual var extra: Any? = null,
    val code: Int = HttpStatusCode.BadRequest.value
)
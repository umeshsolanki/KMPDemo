package kidsTeacher.maths.data

import kotlinx.serialization.Serializable

@Serializable
data class NumberDictionary(
    val numbers: Map<String, NumberData>
)

@Serializable
data class NumberData(
    val en: String, val hi: String
)
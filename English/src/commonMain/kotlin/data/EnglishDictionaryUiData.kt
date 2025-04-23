package data

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class EnglishDictionaryUiData(
    val groups: List<GroupedWordsUiData>
)

@Serializable
data class GroupedWordsUiData(
    val group: String, val words: List<WordUiData>
)

@Serializable
class WordUiData(
    val id: Long? = null,
    val en: String? = null,
    val hi: String? = null,
    var group: String? = null,
    val pos: String? = null,
    val icon: String? = null,
    val createdAt: Instant? = Clock.System.now(),
    val updatedAt: Instant? = null
)

@Serializable
data class GroupUiData(
    val _id: String? = null,
    val id: Int? = null,
    val en: String? = null,
    val hi: String? = null,
    val icon: String? = null,
    val description: String? = null,
    val createdAt: Instant? = Clock.System.now(),
    val updatedAt: Instant? = null
)
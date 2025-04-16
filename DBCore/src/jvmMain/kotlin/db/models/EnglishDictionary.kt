package db.models

data class Word(
    val en: String,
    val hi: String? = null,
    val type: String? = null,
    val pos: List<String>? = null,
)
//    val enPhonetic: String? = null,
//    val hiPhonetic: String? = null,
//    val synonyms: List<String>? = null,
//    val antonyms: List<String>? = null,
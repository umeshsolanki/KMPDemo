package kidsTeacher.english.db

import data.WordUiData
import db.rdms.tables.VARCHAR_LENGTH_100
import db.rdms.tables.VARCHAR_LENGTH_20
import kotlinx.datetime.Clock
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object WordsTable : LongIdTable() {
    val en = varchar("en", VARCHAR_LENGTH_20).nullable()
    val hi = varchar("hi", VARCHAR_LENGTH_100).nullable()
    val pos = varchar("pos", VARCHAR_LENGTH_20).nullable()
    val group = reference("group", WordGroupTable, onDelete = ReferenceOption.SET_NULL).nullable()
    val createdAt = timestamp("created").nullable().default(Clock.System.now())
    val updatedAt = timestamp("updated").nullable()
}

class WordEntity(
    id: EntityID<Long>,
) : LongEntity(id) {
    companion object : LongEntityClass<WordEntity>(WordsTable)

    var en by WordsTable.en
    var hi by WordsTable.hi
    var pos by WordsTable.pos
    var group by WordGroupEntity.referencedOn(WordsTable)
    var createdAt by WordsTable.createdAt
    var updatedAt by WordsTable.updatedAt

    fun toWord(): WordUiData {
        return WordUiData(
            id = id.value, en = en, hi = hi, group = group.en, pos = pos, createdAt = createdAt, updatedAt = updatedAt
        )
    }

    fun fromWord(word: WordUiData): WordEntity {
        return new {
            en = word.en
            hi = word.hi
//            group = word.group
            pos = word.pos
            createdAt = word.createdAt
            updatedAt = word.updatedAt
        }
    }
}


enum class Lang(val code: String) {
    HINDI("hi"), ENGLISH("en")
}

enum class WordType(val type: String) {
    NOUN("noun"), VERB("verb"), ADJECTIVE("adjective"), ADVERB("adverb"), PRONOUN("pronoun"), PREPOSITION("preposition"), CONJUNCTION(
        "conjunction"
    ),
    INTERJECTION("interjection"),


}
package kidsTeacher.english.db

import data.GroupUiData
import db.rdms.tables.VARCHAR_LENGTH_100
import db.rdms.tables.VARCHAR_LENGTH_200
import kotlinx.datetime.Clock
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object WordGroupTable : IntIdTable("word_groups") {
    val en = varchar("en", VARCHAR_LENGTH_100).uniqueIndex().nullable()
    val hi = varchar("hi", VARCHAR_LENGTH_100).nullable()
    val icon = varchar("icon", VARCHAR_LENGTH_100).nullable()
    val description = varchar("description", VARCHAR_LENGTH_200).nullable()
    val createdAt = timestamp("created_at").default(Clock.System.now()).nullable()
    val updatedAt = timestamp("updated_at").nullable()

}

class WordGroupEntity(
    id: EntityID<Int>
) : IntEntity(id) {
    companion object : IntEntityClass<WordGroupEntity>(WordGroupTable)

    var en by WordGroupTable.en
    var hi by WordGroupTable.hi
    var description by WordGroupTable.description
    var createdAt by WordGroupTable.createdAt
    var updatedAt by WordGroupTable.updatedAt

    fun toUiData(): GroupUiData {
        return GroupUiData(
            id = id.value, en = en, hi = hi, description = description, createdAt = createdAt, updatedAt = updatedAt
        )
    }

    fun fromUiData(group: GroupUiData): WordGroupEntity {
        return new {
            en = group.en
            hi = group.hi
            description = group.description
            createdAt = group.createdAt
            updatedAt = group.updatedAt
        }
    }

}
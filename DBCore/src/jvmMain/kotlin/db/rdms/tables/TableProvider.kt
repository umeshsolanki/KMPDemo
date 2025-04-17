package db.rdms.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table

interface TableProvider {
    fun listTables(): List<Table>
}

object TableRegistry {

    private val providers = mutableListOf<TableProvider>()

    fun register(provider: TableProvider) {
        providers.add(provider)
    }

    fun getAllTables(): List<Table> {
        return providers.flatMap { it.listTables() }
    }

}

const val VARCHAR_LENGTH_50 = 50
const val VARCHAR_LENGTH_100 = 100
const val VARCHAR_LENGTH_1000 = 1000
const val VARCHAR_LENGTH_10 = 10


object TestTable : IntIdTable("test") {
    val name = varchar("name", VARCHAR_LENGTH_50)
}

class TestEntity(
    id: EntityID<Int>,
) : IntEntity(id) {
    companion object : IntEntityClass<TestEntity>(TestTable)

    var name by TestTable.name
}


object WordsTable : IntIdTable() {
    val word = varchar("word", VARCHAR_LENGTH_50)
    val meaning = varchar("meaning", VARCHAR_LENGTH_1000)
    val type = varchar("type", VARCHAR_LENGTH_10)
    val lang = varchar("lang", VARCHAR_LENGTH_10)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at")
}
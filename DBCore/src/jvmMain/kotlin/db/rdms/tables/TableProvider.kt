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

const val VARCHAR_LENGTH_3 = 3
const val VARCHAR_LENGTH_50 = 50
const val VARCHAR_LENGTH_20 = 20
const val VARCHAR_LENGTH_100 = 100
const val VARCHAR_LENGTH_200 = 100
const val VARCHAR_LENGTH_500 = 100
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

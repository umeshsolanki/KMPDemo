package db.rdms

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import db.rdms.tables.TableRegistry
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object RdbHelper {

    private const val defaultDb = "kidsTeacher"
    private const val defaultConnection = "jdbc:mysql://localhost:3306/$defaultDb"
    private const val defaultUser = "root"
    private const val defaultPassword = "Mysqldb@0_"

    private val config = HikariConfig().apply {
        jdbcUrl = defaultConnection
        driverClassName = "com.mysql.cj.jdbc.Driver"
        username = defaultUser
        password = defaultPassword
        maximumPoolSize = 6
        // as of version 0.46.0, if these options are set here, they do not need to be duplicated in DatabaseConfig
        isReadOnly = false
        transactionIsolation = "TRANSACTION_SERIALIZABLE"
    }

    private val dataSource = HikariDataSource(config)

    fun getDatabase(): Database {
        return Database.connect(dataSource)
    }

    fun createDbAndTables() {
        transaction(getDatabase()) {
            println("Creating database...")
            SchemaUtils.createDatabase(defaultDb)
            println("Database created")
        }
        transaction {
            print("Creating tables...")
            SchemaUtils.create(*TableRegistry.getAllTables().toTypedArray())
            println("tables created")
        }
    }


}
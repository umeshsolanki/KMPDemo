package db.rdms

object RdbHelper {

    const val defaultConnection = "jdbc:mysql://localhost:3306/"
    const val defaultDb = "kidsTeacher"
    const val defaultUser = "root"
    const val defaultPassword = "Mysqldb@0_$"

    fun getConnectionString(
        db: String = defaultDb, user: String = defaultUser, password: String = defaultPassword
    ): String {
        return "$defaultConnection$db?user=$user&password=$password"
    }

    fun getConnection(
        db: String = defaultDb, user: String = defaultUser, password: String = defaultPassword
    ): java.sql.Connection {
        return java.sql.DriverManager.getConnection(getConnectionString(db, user, password))
    }

    fun closeConnection(connection: java.sql.Connection) {
        try {
            connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getStatement(connection: java.sql.Connection): java.sql.Statement {
        return connection.createStatement()
    }

}
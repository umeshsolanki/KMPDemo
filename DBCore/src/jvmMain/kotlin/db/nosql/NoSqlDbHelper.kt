package db.nosql

import com.mongodb.client.model.Filters.eq
import com.mongodb.client.result.InsertManyResult
import com.mongodb.client.result.InsertOneResult
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.conversions.Bson


object NoSqlDbHelper {

    private const val defaultConnection = "mongodb://localhost:27017"
    const val DefaultDb: String = "kidsTeacher"

    /**
     * @param connectionString MongoDB connection string
     * @return MongoClient instance
     */
    fun getMongoClient(connectionString: String): MongoClient? {
        return if (clientPool.containsKey(connectionString)) {
            clientPool[connectionString]
        } else {
            clientPool[connectionString] = MongoClient.create(connectionString)
            clientPool[connectionString]
        }
    }

    fun getDb(db: String = DefaultDb): MongoDatabase {
        return getMongoClient(defaultConnection)?.getDatabase(db) ?: throw Exception("Unable to connect to MongoDB")
    }

    inline fun <reified T : Any> getCollection(db: String, collection: String): MongoCollection<T> {
        return getDb(db).getCollection<T>(collection)
    }

    suspend inline fun <reified T : Any> insert(data: T): InsertOneResult {
        return getDb().getCollection<T>(T::class.java.simpleName).insertOne(data)
    }

    suspend inline fun <reified T : Any> insert(data: List<T>): InsertManyResult {
        return getDb().getCollection<T>(T::class.java.simpleName).insertMany(data)
    }

    suspend inline fun <reified T : Any> getAll(): List<T> {
        return getDb().getCollection<T>(T::class.java.simpleName).find().toList()
    }

    suspend inline fun <reified T : Any> getById(id: Any): T? {
        return getDb().getCollection<T>(T::class.java.simpleName).find(
            eq(id)
        ).firstOrNull()
    }

    suspend inline fun <reified T : Any> find(filters: Bson): List<T> {
        return getDb().getCollection<T>(T::class.java.simpleName).find(
            filters
        ).toList()
    }


    private val clientPool = mutableMapOf<String?, MongoClient?>()
}
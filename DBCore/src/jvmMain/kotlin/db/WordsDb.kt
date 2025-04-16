package db

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import db.models.Word


object DbHelper {

    private const val defaultConnection = "mongodb://localhost:27017"
    private const val defaultDb = "kidsTeacher"
    const val WORDS_COLLECTION = "words"

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

    fun getDb(db: String = defaultDb): MongoDatabase {
        return getMongoClient(defaultConnection)?.getDatabase(db) ?: throw Exception("Unable to connect to MongoDB")
    }

    fun getCollection(db: String = defaultDb, collection: String = WORDS_COLLECTION): MongoCollection<Word> {
        return getDb(db).getCollection(collection)
    }


    private val clientPool = mutableMapOf<String?, MongoClient?>()
}
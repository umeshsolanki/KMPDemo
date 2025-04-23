package db.nosql.controller

import db.nosql.NoSqlDbHelper
import network.ErrorResponseWrapper
import network.ResponseWrapper
import org.bson.conversions.Bson
import java.lang.reflect.ParameterizedType

open class BaseNoSqlCrudController<E> {

    val typeClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<E>

    suspend inline fun <reified T : Any> save(data: T): Boolean {
        return try {
            NoSqlDbHelper.insert(data)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend inline fun <reified T : Any> save(data: List<T>): Boolean {
        return try {
            NoSqlDbHelper.insert(data)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend inline fun <reified T : Any> getAll(): List<T> {
        return try {
            NoSqlDbHelper.getAll<T>()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend inline fun <reified T : Any> findById(id: Any): T? {
        return NoSqlDbHelper.getById<T>(id)
    }

    suspend inline fun <reified T : Any> find(filters: Bson): List<T> {
        return NoSqlDbHelper.find<T>(filters)
    }

    fun getErrorWrapper(msg: String = "Error"): ErrorResponseWrapper<E> {
        return ErrorResponseWrapper<E>(msg)
    }

    fun getResponseWrapper(msg: String? = "Success"): ResponseWrapper<E> {
        return ResponseWrapper<E>(msg = msg)
    }

}
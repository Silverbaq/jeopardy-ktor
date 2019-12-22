package dk.w4.Repository

import dk.w4.model.Team

interface Repository<T> {
    suspend fun add(data: T)
    suspend fun getById(id: Int): T?
    suspend fun getById(id: String): T?
    suspend fun getAll(): List<T>
    suspend fun update(item: T): T
    suspend fun removeById(id: Int): Boolean
    suspend fun removeById(id: String): Boolean
    suspend fun clear()
}
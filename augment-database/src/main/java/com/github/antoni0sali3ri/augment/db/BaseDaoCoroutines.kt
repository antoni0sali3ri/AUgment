package com.github.antoni0sali3ri.augment.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

/**
 * Generic DAO interface to squash boilerplate code.
 *
 * Same as {BaseDao} but uses suspend functions instead.
 *
 * @param T the type of values processed by this DAO. Needs to be an entity class.
 * @see androidx.room.Entity
 * @see BaseDao
 */
@Dao
interface BaseDaoCoroutines<T : RoomEntity> {

    @Insert
    suspend fun insert(value: T)

    @Insert
    suspend fun insertAll(values: List<T>)

    @Update
    suspend fun update(value: T)

    @Update
    suspend fun updateAll(values: List<T>)

    @Delete
    suspend fun delete(value: T)

    @Delete
    suspend fun deleteAll(values: List<T>)

    suspend fun deleteById(id: Long) : T

    fun getSingle(id: Long) : T

    fun getAll(): LiveData<List<T>>

}
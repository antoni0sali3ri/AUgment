package com.github.antoni0sali3ri.augment.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

/**
 * Generic DAO interface to squash boilerplate code.
 *
 * @param T the type of values processed by this DAO. Needs to be an entity class.
 * @see androidx.room.Entity
 */
@Dao
interface BaseDao<T : RoomEntity> {

    @Insert
    fun insert(value: T)

    @Insert
    fun insertAll(values: List<T>)

    @Update
    fun update(value: T)

    @Update
    fun updateAll(values: List<T>)

    @Delete
    fun delete(value: T)

    @Delete
    fun deleteAll(values: List<T>)

    fun deleteById(id: Long)

    fun getSingle(id: Long): T

    fun getAll(): LiveData<List<T>>

}
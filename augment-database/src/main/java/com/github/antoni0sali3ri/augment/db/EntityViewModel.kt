package com.github.antoni0sali3ri.augment.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EntityViewModel<T : RoomEntity>(application: Application, val dao: BaseDaoCoroutines<T>) :
    AndroidViewModel(application) {

    val items: LiveData<out List<T>> by lazy { dao.getAll() }

    fun getSingle(id: Long) = dao.getSingle(id)

    fun insert(item: T) = viewModelScope.launch {
        dao.insert(item)
    }

    fun update(item: T) = viewModelScope.launch {
        dao.update(item)
    }

    fun update(items: List<T>) = viewModelScope.launch {
        dao.updateAll(items)
    }

    fun delete(item: T) = viewModelScope.launch {
        dao.delete(item)
    }

    fun delete(id: Long) = viewModelScope.launch {
        dao.deleteById(id)
    }
}

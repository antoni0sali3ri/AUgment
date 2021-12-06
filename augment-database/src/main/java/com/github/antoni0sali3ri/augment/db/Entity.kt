package com.github.antoni0sali3ri.augment.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
abstract class RoomEntity(@PrimaryKey val id : Long = 0L)
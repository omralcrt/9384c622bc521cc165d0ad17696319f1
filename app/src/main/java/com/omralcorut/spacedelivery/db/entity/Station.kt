package com.omralcorut.spacedelivery.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Station(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "coordinate_x") val coordinateX: Int = 0,
    @ColumnInfo(name = "coordinate_y") val coordinateY: Int = 0,
    @ColumnInfo(name = "capacity") val capacity: Int? = null,
    @ColumnInfo(name = "stock") var stock: Int? = null,
    @ColumnInfo(name = "need") var need: Int? = null,
    @ColumnInfo(name = "favorite") var favorite: Boolean = false,
)
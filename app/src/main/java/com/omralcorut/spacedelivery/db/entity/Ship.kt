package com.omralcorut.spacedelivery.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ship(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "durability") val durability: Int? = null,
    @ColumnInfo(name = "speed") val speed: Int? = null,
    @ColumnInfo(name = "capacity") val capacity: Int? = null,
    @ColumnInfo(name = "damage_capacity") var damageCapacity: Int = 100,
    @ColumnInfo(name = "spacesuit_count") var spacesuitCount: Int? = null,
    @ColumnInfo(name = "universal_space_time") var universalSpaceTime: Int? = null,
    @ColumnInfo(name = "durability_period") val durabilityPeriod: Int? = null,
    @ColumnInfo(name = "station_name") var stationName: String = "Dünya",
)
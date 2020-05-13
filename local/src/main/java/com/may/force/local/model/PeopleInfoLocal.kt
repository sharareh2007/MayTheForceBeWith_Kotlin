package com.may.force.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "PeopleInfoLocal")
data class PeopleInfoLocal(

    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "height") val height: String,
    @ColumnInfo(name = "mass") val mass: String,
    @ColumnInfo(name = "hair_color") val hair_color: String,
    @ColumnInfo(name = "skin_color") val skin_color: String,
    @ColumnInfo(name = "eye_color") val eye_color: String,
    @ColumnInfo(name = "birth_year") val birth_year: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "homeworld") val homeworld: String,
    @ColumnInfo(name = "films") val listFilms: List<String>,
    @ColumnInfo(name = "species") val listSpecies: List<String>,
    @ColumnInfo(name = "vehicles") val listVehicles: List<String>,
    @ColumnInfo(name = "starships") val listStarships: List<String>,
    @ColumnInfo(name = "created") val dateCreated: Date,
    @ColumnInfo(name = "edited") val dateEdited: Date,
    @ColumnInfo(name = "url") val url: String
)
package com.may.force.data.model

import java.util.*

data class PeopleInfoData(
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeWorld: String,
    val listFilms: List<String>,
    val listSpecies: List<String>,
    val listVehicles: List<String>,
    val listStarships: List<String>,
    val dateCreated: Date,
    val dateEdited: Date,
    val url: String
)
package com.may.force.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*


data class PeopleInfoNetwork(

    @SerializedName("name") val name: String,
    @SerializedName("height") val height: String,
    @SerializedName("mass") val mass: String,
    @SerializedName("hair_color") val hair_color: String,
    @SerializedName("skin_color") val skin_color: String,
    @SerializedName("eye_color") val eye_color: String,
    @SerializedName("birth_year") val birth_year: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("homeworld") val homeworld: String,
    @SerializedName("films") val listFilms: List<String>,
    @SerializedName("species") val listSpecies: List<String>,
    @SerializedName("vehicles") val listVehicles: List<String>,
    @SerializedName("starships") val listStarships: List<String>,
    @SerializedName("created") val dateCreated: Date,
    @SerializedName("edited") val dateEdited: Date,
    @SerializedName("url") val url: String
)
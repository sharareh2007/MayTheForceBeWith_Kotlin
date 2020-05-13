package com.may.force.remote.mapper

import com.may.force.data.model.PeopleInfoDetailsData
import com.may.force.remote.model.PeopleInfoDetailsNetwork
import javax.inject.Inject


class PeopleInfoDetailsDataNetworkMapper  @Inject constructor(): Mapper<PeopleInfoDetailsData, PeopleInfoDetailsNetwork> {
    override fun from(e: PeopleInfoDetailsNetwork): PeopleInfoDetailsData {
        return PeopleInfoDetailsData(
            name = e.name,
            height = e.height,
            mass = e.mass,
            hair_color = e.hair_color,
            skin_color = e.skin_color,
            eye_color = e.eye_color,
            birth_year = e.birth_year,
            gender = e.gender,
            homeWorld = e.homeworld,
            listFilms = e.listFilms,
            listSpecies = e.listSpecies,
            listVehicles = e.listVehicles,
            listStarships = e.listStarships,
            dateCreated = e.dateCreated,
            dateEdited = e.dateEdited,
            url = e.url
        )
    }

    override fun to(t: PeopleInfoDetailsData): PeopleInfoDetailsNetwork {
        return PeopleInfoDetailsNetwork(
            name = t.name,
            height = t.height,
            mass = t.mass,
            hair_color = t.hair_color,
            skin_color = t.skin_color,
            eye_color = t.eye_color,
            birth_year = t.birth_year,
            gender = t.gender,
            homeworld = t.homeWorld,
            listFilms = t.listFilms,
            listSpecies = t.listSpecies,
            listVehicles = t.listVehicles,
            listStarships = t.listStarships,
            dateCreated = t.dateCreated,
            dateEdited = t.dateEdited,
            url = t.url
        )
    }
}
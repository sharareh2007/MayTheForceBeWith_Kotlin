package com.may.force.presentation.mapper


import com.may.force.domain.entities.PeopleInfoEntity
import com.may.force.presentation.model.PeopleInfo
import javax.inject.Inject

class PeopleInfoEntityMapper @Inject constructor() : Mapper<PeopleInfoEntity, PeopleInfo> {
    override fun from(e: PeopleInfo): PeopleInfoEntity {
        return PeopleInfoEntity(
            name = e.name,
            height = e.height,
            mass = e.mass,
            hair_color = e.hair_color,
            skin_color = e.skin_color,
            eye_color = e.eye_color,
            birth_year = e.birth_year,
            gender = e.gender,
            homeWorld = e.homeWorld,
            listFilms = e.listFilms,
            listSpecies = e.listSpecies,
            listVehicles = e.listVehicles,
            listStarships = e.listStarships,
            dateCreated = e.dateCreated,
            dateEdited = e.dateEdited,
            url = e.url
        )
    }

    override fun to(t: PeopleInfoEntity): PeopleInfo {
        return PeopleInfo(
            name = t.name,
            height = t.height,
            mass = t.mass,
            hair_color = t.hair_color,
            skin_color = t.skin_color,
            eye_color = t.eye_color,
            birth_year = t.birth_year,
            gender = t.gender,
            homeWorld = t.homeWorld,
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
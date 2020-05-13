package com.may.force.domain.utils


import com.may.force.domain.entities.PeopleInfoDetailsEntity
import com.may.force.domain.entities.PeopleInfoEntity
import java.util.*

class TestDataGenerator {

    companion object {
        fun generatePeopleInfo(): List<PeopleInfoEntity> {
            val t1 = PeopleInfoEntity(
                "anna",
                "160",
                "50",
                "brown",
                "white",
                "brown",
                "1988",
                "female",
                "homeWorld1",
                listOf("film3", "film4"),
                listOf("species3", "speices4"),
                listOf("veicles3", "veicels4"),
                listOf("starships3", "starships4"),
                Date(),
                Date(),
                "www.test2.com"

            )
            val t2 = PeopleInfoEntity(
                "anna",
                "160",
                "50",
                "brown",
                "white",
                "brown",
                "1988",
                "female",
                "homeWorld2",
                listOf("film3", "film4"),
                listOf("species3", "speices4"),
                listOf("veicles3", "veicels4"),
                listOf("starships3", "starships4"),
                Date(),
                Date(),
                "www.test2.com"

            )
            val t3 = PeopleInfoEntity(
                "anna",
                "160",
                "50",
                "brown",
                "white",
                "brown",
                "1988",
                "female",
                "homeWorld3",
                listOf("film3", "film4"),
                listOf("species3", "speices4"),
                listOf("veicles3", "veicels4"),
                listOf("starships3", "starships4"),
                Date(),
                Date(),
                "www.test2.com"

            )

            return listOf(t1, t2, t3)
        }


        fun generatePeopleInfoDetails(): PeopleInfoDetailsEntity {
            return PeopleInfoDetailsEntity(
                "anna",
                "160",
                "50",
                "brown",
                "white",
                "brown",
                "1988",
                "female",
                "homeWorld",
                listOf("film3", "film4"),
                listOf("species3", "speices4"),
                listOf("veicles3", "veicels4"),
                listOf("starships3", "starships4"),
                Date(),
                Date(),
                "www.test2.com"

            )
        }
    }
}
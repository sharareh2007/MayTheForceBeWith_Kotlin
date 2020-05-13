package com.may.force.remote.utils

import com.may.force.remote.model.PeopleInfoDetailsNetwork
import com.may.force.remote.model.PeopleInfoNetwork
import java.util.*

class TestDataGenerator {

    companion object {

        fun generatePeopleInfo(): List<PeopleInfoNetwork> {
            val t1 = PeopleInfoNetwork(
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
            val t2 = PeopleInfoNetwork(
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
            val t3 = PeopleInfoNetwork(
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

        fun generatePeopleInfoDetails(): PeopleInfoDetailsNetwork {
            return PeopleInfoDetailsNetwork(
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
package com.may.force.local.utils.utils

import com.may.force.local.model.PeopleInfoDetailsLocal
import java.util.*


class TestDataGenerator {

    companion object {
        fun generatePeopleInfoDetails(): PeopleInfoDetailsLocal {
            return PeopleInfoDetailsLocal(
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

        fun generatePeopleInfo(): List<PeopleInfoDetailsLocal> {
            val t1 = PeopleInfoDetailsLocal(
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
            val t2 = PeopleInfoDetailsLocal(
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
            val t3 = PeopleInfoDetailsLocal(
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
}
}
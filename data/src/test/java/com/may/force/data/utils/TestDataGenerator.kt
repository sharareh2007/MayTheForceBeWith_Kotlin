package com.may.force.data.utils

import com.may.force.data.model.PeopleInfoData
import com.may.force.data.model.PeopleInfoDetailsData
import java.util.*


class TestDataGenerator {
    companion object {
        fun generatePeopleInfoData(): List<PeopleInfoData> {
            val t1 = PeopleInfoData(
                "anna",
                "160",
                "50",
                "brown",
                "white",
                "brown",
                "1988",
                "female",
                "homeworld1",
                listOf("film3", "film4"),
                listOf("species3", "speices4"),
                listOf("veicles3", "veicels4"),
                listOf("starships3", "starships4"),
                Date(),
                Date(),
                "www.test2.com"

            )
            val t2 = PeopleInfoData(
                "anna",
                "160",
                "50",
                "brown",
                "white",
                "brown",
                "1988",
                "female",
                "homeworld2",
                listOf("film3", "film4"),
                listOf("species3", "speices4"),
                listOf("veicles3", "veicels4"),
                listOf("starships3", "starships4"),
                Date(),
                Date(),
                "www.test2.com"

            )
            val t3 = PeopleInfoData(
                "anna",
                "160",
                "50",
                "brown",
                "white",
                "brown",
                "1988",
                "female",
                "homeworld3",
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


        fun generatePeopleInfoDetailsData(): PeopleInfoDetailsData {
            return PeopleInfoDetailsData(
                "anna",
                "160",
                "50",
                "brown",
                "white",
                "brown",
                "1988",
                "female",
                "homeworld",
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
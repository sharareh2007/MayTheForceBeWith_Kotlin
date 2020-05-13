package org.drulabs.bankbuddy.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.may.force.local.databas.ChallengeDB
import com.may.force.local.databas.PeopleInfoDetailsDAO
import com.may.force.local.utils.TestData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class PeopleInfoDetailsDAOTest {

    private lateinit var challengeDB: ChallengeDB
    private lateinit var peopleInfoDetailsDAO: PeopleInfoDetailsDAO

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        challengeDB = Room.inMemoryDatabaseBuilder(context, ChallengeDB::class.java)
            .allowMainThreadQueries()
            .build()

        peopleInfoDetailsDAO = challengeDB.getPeopleInfoDetailsDao()
    }

    @After
    fun tearDown() {
        challengeDB.close()
    }

    @Test
    fun test_getPeopleInfoDetails() {
        val peopleInfoDetails = TestData.generatePeopleInfoDetails()
        val page = 1

        peopleInfoDetailsDAO.getPeopleDetailsInfo(page)

        peopleInfoDetailsDAO.getPeopleDetailsInfo(page)
            .test()
            .assertValue {
                it == peopleInfoDetails
            }.assertNotComplete() // As Room Observables are kept alive
    }

}
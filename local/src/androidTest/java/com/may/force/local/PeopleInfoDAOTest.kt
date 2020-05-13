package org.drulabs.bankbuddy.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.may.force.local.databas.ChallengeDB
import com.may.force.local.databas.PeopleInfoDAO
import com.may.force.local.utils.TestData
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class PeopleInfoDAOTest {

    private lateinit var challengeDB: ChallengeDB
    private lateinit var peopleInfoDAO: PeopleInfoDAO

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        challengeDB = Room.inMemoryDatabaseBuilder(context, ChallengeDB::class.java)
            .allowMainThreadQueries()
            .build()

        peopleInfoDAO = challengeDB.getPeopleInfoDao()
    }

    @After
    fun tearDown() {
        challengeDB.close()
    }


    @Test
    fun test_savePeopleInfo() {

        val peopleList = TestData.generatePeopleInfo()

        peopleInfoDAO.savePeopleInfo(peopleList[0])

        peopleInfoDAO.getPeopleInfo()
            .test()
            .assertValue {
                it == peopleList
            }.assertNotComplete() // As Room Observables are kept alive
    }

}
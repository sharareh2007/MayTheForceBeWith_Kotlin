package com.may.force.local.utils

import com.may.force.data.repository.LocalDataSource
import com.may.force.local.databas.PeopleInfoDAO
import com.may.force.local.databas.PeopleInfoDetailsDAO
import com.may.force.local.mapper.PeopleInfoDataLocalMapper
import com.may.force.local.mapper.PeopleInfoDetailsDataLocalMapper
import com.may.force.local.source.LocalDataSourceImpl
import com.may.force.local.utils.utils.TestDataGenerator
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class LocalDataSourcePeopleInfoTest {

    private val peopleInfoMapper = PeopleInfoDataLocalMapper()
    private val peopleInfoDetailsMapper = PeopleInfoDetailsDataLocalMapper()

    @Mock
    private lateinit var peopleInfoDAO: PeopleInfoDAO

    @Mock
    private lateinit var peopleInfoDetailsDAO: PeopleInfoDetailsDAO

    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        localDataSource = LocalDataSourceImpl(
            peopleInfoMapper,
            peopleInfoDetailsMapper,
            peopleInfoDAO,
            peopleInfoDetailsDAO
        )
    }

    @Test
    fun test_getPeopleInfo_success() {

        val mockPeopleInfo = TestDataGenerator.generatePeopleInfo()

        Mockito.`when`(peopleInfoDAO.getPeopleInfo())
            .thenReturn(Observable.just(mockPeopleInfo))

        localDataSource.getPeopleInfo()
            .test()
            .assertSubscribed()
            .assertValue { peopleList ->
                mockPeopleInfo.containsAll(
                    peopleList.map {
                        peopleInfoMapper.to(it)
                    }
                )
            }
    }


    @Test
    fun test_getPeopleInfo_error() {

        val errorMsg = "ERROR"

        Mockito.`when`(peopleInfoDAO.getPeopleInfo())
            .thenReturn(Observable.error(Throwable(errorMsg)))

        localDataSource.getPeopleInfo()
            .test()
            .assertSubscribed()
            .assertError {
                it.message == errorMsg
            }
            .assertNotComplete()
    }


    @Test
    fun test_savePeopleInfo_success() {

        val mockPeopleInfo = TestDataGenerator.generatePeopleInfo()[0]


        localDataSource.savePeopleInfo(
            peopleInfoMapper.from(mockPeopleInfo)
        )

        Mockito.verify(peopleInfoDAO, Mockito.times(1))
            .savePeopleInfo(mockPeopleInfo)
    }

}

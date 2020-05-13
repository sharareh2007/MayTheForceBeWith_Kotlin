package com.may.force.local.utils

import com.may.force.data.repository.LocalDataSource
import com.may.force.local.databas.PeopleInfoDAO
import com.may.force.local.databas.PeopleInfoDetailsDAO
import com.may.force.local.mapper.PeopleInfoDataLocalMapper
import com.may.force.local.mapper.PeopleInfoDetailsDataLocalMapper
import com.may.force.local.source.LocalDataSourceImpl
import com.may.force.local.utils.utils.TestDataGenerator
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class LocalDataSourcePeopleInfoDetailsTest {

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
    fun test_getPeopleInfoDetails_success() {
        val page = 1
        val peopleInfoDetailsLocal = TestDataGenerator.generatePeopleInfoDetails()

        Mockito.`when`(peopleInfoDetailsDAO.getPeopleDetailsInfo(page))
            .thenReturn(Observable.just(peopleInfoDetailsLocal))

        localDataSource.getPeopleInfoDetails(page)
            .test()
            .assertSubscribed()
            .assertValue { it == peopleInfoDetailsMapper.from(peopleInfoDetailsLocal) }
    }
}

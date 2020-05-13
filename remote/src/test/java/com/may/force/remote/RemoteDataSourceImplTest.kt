package com.may.force.remote

import com.may.force.data.repository.RemoteDataSource
import com.may.force.remote.api.ChallengeService
import com.may.force.remote.mapper.PeopleInfoDataNetworkMapper
import com.may.force.remote.mapper.PeopleInfoDetailsDataNetworkMapper
import com.may.force.remote.model.ResponseWrapper
import com.may.force.remote.source.RemoteDataSourceImpl
import com.may.force.remote.utils.TestDataGenerator
import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RemoteDataSourceImplTest {

    @Mock
    private lateinit var challengeService: ChallengeService

    private val peopleInfoMapper = PeopleInfoDataNetworkMapper()
    private val peopleInfoDetailsMapper = PeopleInfoDetailsDataNetworkMapper()

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = RemoteDataSourceImpl(
            peopleInfoMapper,
            peopleInfoDetailsMapper,
            challengeService
        )
    }

    @Test
    fun test_getPeopleInfo_success() {
        val peopleInfoNetwork = TestDataGenerator.generatePeopleInfo()
        val peopleInfoDetailsNetwork = TestDataGenerator.generatePeopleInfoDetails()

        val mockResponse = ResponseWrapper(
            peopleInfoNetwork,
            peopleInfoDetailsNetwork
        )

        Mockito.`when`(challengeService.getPeopleInfo())
            .thenReturn(Observable.just(mockResponse))


        remoteDataSource.getPeopleInfo()
            .test()
            .assertSubscribed()
            .assertValue { peopleInfo ->
                peopleInfo.containsAll(
                    peopleInfoNetwork.map {
                        peopleInfoMapper.from(it) }
                )
            }
            .assertComplete()

        Mockito.verify(challengeService, Mockito.times(1))
            .getPeopleInfo()
    }

    @Test
    fun test_getPeopleInfo_error() {

        val errorMsg = "ERROR"

        Mockito.`when`(challengeService.getPeopleInfo())
            .thenReturn(Observable.error(Throwable(errorMsg)))

        remoteDataSource.getPeopleInfo()
            .test()
            .assertSubscribed()
            .assertError {
                it.message == errorMsg
            }
            .assertNotComplete()
    }

    @Test
    fun test_getPeopleInfoDetails_success() {
        val peopleInfoNetwork = TestDataGenerator.generatePeopleInfo()
        val peopleInfoDetailsNetwork = TestDataGenerator.generatePeopleInfoDetails()
        val page = 1

        val mockResponse = ResponseWrapper(
            peopleInfoNetwork,
            peopleInfoDetailsNetwork
        )

        Mockito.`when`(challengeService.getPeopleInfo())
            .thenReturn(Observable.just(mockResponse))

        remoteDataSource.getPeopleInfoDetails(page)
            .test()
            .assertSubscribed()
            .assertValue {
                val data = peopleInfoDetailsMapper.to(it)
                data == peopleInfoDetailsNetwork
            }
            .assertComplete()

    }

    @Test
    fun test_getPeopleInfoDetails_error() {
        val page = 1
        val errorMsg = "ERROR"

        Mockito.`when`(challengeService.getPeopleInfo())
            .thenReturn(Observable.error(Throwable(errorMsg)))

        remoteDataSource.getPeopleInfoDetails(page)
            .test()
            .assertSubscribed()
            .assertError {
                it.message == errorMsg
            }
            .assertNotComplete()
    }

}
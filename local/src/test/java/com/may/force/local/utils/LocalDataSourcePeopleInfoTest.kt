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

        val peopleInfoLocal = TestDataGenerator.generatePeopleInfo()

        Mockito.`when`(peopleInfoDAO.getPeopleInfo())
            .thenReturn(Observable.just(ListpeopleInfoLocal))


        localDataSource.getPeopleInfo()
            .test()
            .assertSubscribed()
            .assertValue { peopleList ->
                peopleInfoLocal.containsAll(
                    peopleList.map {
                        peopleInfoMapper.to(it)
                    }
                )
            }
    }

    @Test
    fun test_savePeopleInfo_success() {
        val mockPeopleInfo = TestDataGenerator.generatePeopleInfo()

        localDataSource.savePeopleInfo(
            mockPeopleInfo.map {
                peopleInfoMapper.from(it)
            }
        )

        Mockito.verify(transactionDao, Mockito.times(1))
            .addTransactions(mockTransactions)
    }

    @Test
    fun test_getTransactionById_success() {
        val mockTransaction = TestDataGenerator.generateTransactions()[0]
        val transactionId = mockTransaction.transactionId

        Mockito.`when`(transactionDao.getTransactionById(transactionId))
            .thenReturn(Observable.just(mockTransaction))

        localDataSource.getTransaction(transactionId)
            .test()
            .assertSubscribed()
            .assertValue {
                mockTransaction == transactionMapper.to(it)
            }
    }

    @Test
    fun test_getTransactionById_error() {
        val transactionId = "1234abcde"
        val errorMsg = "ERROR"

        Mockito.`when`(transactionDao.getTransactionById(transactionId))
            .thenReturn(Observable.error(Throwable(errorMsg)))

        localDataSource.getTransaction(transactionId)
            .test()
            .assertSubscribed()
            .assertError {
                it.message == errorMsg
            }
            .assertNotComplete()
    }




}

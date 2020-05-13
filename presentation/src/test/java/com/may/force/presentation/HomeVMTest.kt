package com.may.force.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.may.force.domain.repository.ChallengeRepository
import com.may.force.domain.usecases.GetPeopleInfoTask
import com.may.force.presentation.mapper.PeopleInfoEntityMapper
import com.may.force.presentation.model.Status
import com.may.force.presentation.utils.TestDataGenerator
import com.may.force.presentation.viewModels.HomeVM
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HomeVMTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ChallengeRepository

    private lateinit var homeVM: HomeVM
    private val peopleInfoMapper = PeopleInfoEntityMapper()


    private val peopleInfo = TestDataGenerator.generatePeopleInfo()
    private val peopleInfoEntities =
        peopleInfo.map { peopleInfoMapper.from(it) }


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val getPeopleInfoTask = GetPeopleInfoTask(
            repository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )

        Mockito.`when`(repository.getPeopleInfo(

        ))
            .thenReturn(Observable.just(peopleInfoEntities))

        homeVM = HomeVM(
            peopleInfoMapper,
            getPeopleInfoTask
        )

    }

    @Test
    fun test_getPeopleInfo_success() {

        Mockito.`when`(repository.getPeopleInfo())
            .thenReturn(Observable.just(peopleInfoEntities))

        val peopleInfoResource = homeVM.peopleInfoResource

        peopleInfoResource.observeForever { /*Do nothing*/ }

        Assert.assertTrue(
            peopleInfoResource.value?.status == Status.SUCCESS
                    && peopleInfoResource.value?.data == peopleInfo
        )
    }

    @Test
    fun test_getPeopleInfo_error() {
        val errorMsg = "people info error in fetching data"
        Mockito.`when`(repository.getPeopleInfo())
            .thenReturn(Observable.error(Throwable(errorMsg)))

        val peopleInfoResource = homeVM.peopleInfoResource

        peopleInfoResource.observeForever { /*Do nothing*/ }

        Assert.assertTrue(
            peopleInfoResource.value?.status == Status.ERROR
                    && peopleInfoResource.value?.message == errorMsg
        )
    }

}
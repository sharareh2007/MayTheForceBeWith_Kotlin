package com.may.force.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.may.force.domain.repository.ChallengeRepository
import com.may.force.domain.usecases.GetPeopleInfoDetailsTask
import com.may.force.presentation.mapper.PeopleInfoDetailsEntityMapper
import com.may.force.presentation.model.Status
import com.may.force.presentation.utils.TestDataGenerator
import com.may.force.presentation.viewModels.DetailsVM
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
class DetailsVMTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ChallengeRepository

    private lateinit var detailsVM: DetailsVM
    private val peopleInfoDetailsMapper = PeopleInfoDetailsEntityMapper()

    private val peopleInfoDetails = TestDataGenerator.generatePeopleInfoDetail()
    private val peopleInfoDetailsEntity = peopleInfoDetailsMapper.from(peopleInfoDetails)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val getPeopleInfoDetailsTask = GetPeopleInfoDetailsTask(
            repository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )

        detailsVM = DetailsVM(
            ArgumentMatchers.anyInt(),
            peopleInfoDetailsMapper,
            getPeopleInfoDetailsTask
        )
    }

    @Test
    fun test_getPeopleInfoDetails_success() {

        Mockito.`when`(repository.getPeopleInfoDetails(ArgumentMatchers.anyInt()))
            .thenReturn(Observable.just(peopleInfoDetailsEntity))

        val peopleInfoDetailsResource = detailsVM.peopleInfoDetailsResource

        peopleInfoDetailsResource.observeForever { /*Do nothing*/ }

        Assert.assertTrue(
            peopleInfoDetailsResource.value?.status == Status.SUCCESS
                    && peopleInfoDetailsResource.value?.data == peopleInfoDetails
        )
    }

    @Test
    fun test_getPeopleInfoDetails_error() {
        val errorMsg = "people info error in fetching data"
        Mockito.`when`(repository.getPeopleInfoDetails(ArgumentMatchers.anyInt()))
            .thenReturn(Observable.error(Throwable(errorMsg)))

        val peopleInfoDetailsResource = detailsVM.peopleInfoDetailsResource

        peopleInfoDetailsResource.observeForever { /*Do nothing*/ }

        Assert.assertTrue(
            peopleInfoDetailsResource.value?.status == Status.ERROR
                    && peopleInfoDetailsResource.value?.message == errorMsg
        )
    }

}
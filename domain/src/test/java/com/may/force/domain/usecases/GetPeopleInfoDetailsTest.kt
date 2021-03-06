package com.may.force.domain.usecases


import com.may.force.domain.repository.ChallengeRepository
import com.may.force.domain.utils.TestDataGenerator
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class GetPeopleInfoDetailsTest {

    private lateinit var getPeopleInfoDetailsTask: GetPeopleInfoDetailsTask
    @Mock
    lateinit var challengeRepository: ChallengeRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getPeopleInfoDetailsTask = GetPeopleInfoDetailsTask(
            challengeRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun test_getPeopleInfoDetails_success() {
        val page = 1
        val details = TestDataGenerator.generatePeopleInfoDetails()

        Mockito.`when`(challengeRepository.getPeopleInfoDetails(page))
            .thenReturn(Observable.just(details))

        val testObserver = getPeopleInfoDetailsTask.buildUseCase(
            GetPeopleInfoDetailsTask.Params(
                page
            )
        ).test()

        testObserver.assertSubscribed()
            .assertValue { it == details }
            .assertComplete()

    }

    @Test
    fun test_PeopleInfoDetails_error() {
        val page = 10
        val errorMsg = "ERROR OCCURRED"

        Mockito.`when`(challengeRepository.getPeopleInfoDetails(page))
            .thenReturn(Observable.error(Throwable(errorMsg)))

        val testObserver = getPeopleInfoDetailsTask.buildUseCase(
            GetPeopleInfoDetailsTask.Params(
               page
            )
        ).test()

        testObserver
            .assertSubscribed()
            .assertError { it.message?.equals(errorMsg, false) ?: false }
            .assertNotComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_PeopleInfoDetailsNoParams_error() {
        val testObserver = getPeopleInfoDetailsTask.buildUseCase().test()
        testObserver.assertSubscribed()
    }

}
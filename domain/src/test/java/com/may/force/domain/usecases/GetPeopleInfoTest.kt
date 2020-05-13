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
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetPeopleInfoTest {

    private lateinit var getPeopleInfoTask: GetPeopleInfoTask

    @Mock
    private lateinit var challengeRepository: ChallengeRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getPeopleInfoTask = GetPeopleInfoTask(
            challengeRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun test_getPeopleInfo_success() {

        val peopleInfo = TestDataGenerator.generatePeopleInfo()

        Mockito.`when`(challengeRepository.getPeopleInfo())
            .thenReturn(Observable.just(peopleInfo))

        val testObserver = getPeopleInfoTask.buildUseCase().test()

        Mockito.verify(challengeRepository, times(1))
            .getPeopleInfo()


        testObserver
            .assertSubscribed()
            .assertValue { it.containsAll(peopleInfo) }

    }

    @Test
    fun test_getPeopleInfo_error() {

        val peopleInfo = TestDataGenerator.generatePeopleInfo()
        val errorMsg = "ERROR OCCURRED"

        Mockito.`when`(challengeRepository.getPeopleInfo())
            .thenReturn(Observable.error(Throwable(errorMsg)))

        val testObserver = getPeopleInfoTask.buildUseCase().test()

        Mockito.verify(challengeRepository, times(1))
            .getPeopleInfo()

        testObserver.assertSubscribed()
            .assertError { it.message?.equals(errorMsg) ?: false }
            .assertNotComplete()
    }
/*
    @Test
    fun test_AccountUpgradeEligibility() {
        val upgradableUserInfo = TestDataGenerator.generateUpgradableUserInfo()
        assert(upgradableUserInfo.isEligibleForUpgrade)

        val userInfo = TestDataGenerator.generateUserInfo()
        assert(!userInfo.isEligibleForUpgrade)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_getUserInfoNoParameters_error() {
        val userInfo = TestDataGenerator.generateUserInfo()
        userInfo.accountNumber

        val testObserver = getPeopleInfoTask.buildUseCase().test()
        testObserver.assertSubscribed()
    }
*/
}
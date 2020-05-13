
import com.may.force.data.mapper.PeopleInfoDetailsDomainDataMapper
import com.may.force.data.mapper.PeopleInfoDomainDataMapper
import com.may.force.data.repository.ChallengeRepositoryImpl
import com.may.force.data.repository.LocalDataSource
import com.may.force.data.repository.RemoteDataSource
import com.may.force.data.utils.TestDataGenerator
import com.may.force.domain.repository.ChallengeRepository
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RepositoryImplementationTest {

    private lateinit var challengeRepository: ChallengeRepository

    private val PeopleInfoMapper = PeopleInfoDomainDataMapper()
    private val PeopleInfoDetailsMapper = PeopleInfoDetailsDomainDataMapper()

    @Mock
    private lateinit var localDataSource: LocalDataSource
    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        challengeRepository = ChallengeRepositoryImpl(
            PeopleInfoMapper,
            PeopleInfoDetailsMapper,
            localDataSource,
            remoteDataSource
        )
    }

    @Test
    fun test_getPeopleInfo_local_remote() {

        val peopleInfoData = TestDataGenerator.generatePeopleInfoData()

        Mockito.`when`(remoteDataSource.getPeopleInfo())
            .thenReturn(Observable.just(peopleInfoData))
        Mockito.`when`(localDataSource.getPeopleInfo())
            .thenReturn(Observable.just(peopleInfoData))

        val testSubscriber = challengeRepository.getPeopleInfo().test()

        testSubscriber.assertSubscribed()
            .assertValues(
                peopleInfoData.map { PeopleInfoMapper.from(it) },
                peopleInfoData.map { PeopleInfoMapper.from(it) }
            )
            .assertComplete()

        // Mockito.verify(localDataSource, times(1))
        //  .savePeopleInfo(peopleIdentifier, favorite)

        Mockito.verify(remoteDataSource, times(1))
            .getPeopleInfo()
    }

    @Test
    fun test_getPeopleInfoDetails_local_remote() {
        val page = 1
        val peopleInfoDetailsData = TestDataGenerator.generatePeopleInfoDetailsData()
        val peopleInfoDetailsDomain = PeopleInfoDetailsMapper.from(peopleInfoDetailsData)

        Mockito.`when`(remoteDataSource.getPeopleInfoDetails(page))
            .thenReturn(Observable.just(peopleInfoDetailsData))
        Mockito.`when`(localDataSource.getPeopleInfoDetails(page))
            .thenReturn(Observable.just(peopleInfoDetailsData))

        val testSubscriber = challengeRepository.getPeopleInfoDetails(page).test()

        testSubscriber.assertSubscribed()
            .assertValueCount(2)
            .assertValues(peopleInfoDetailsDomain, peopleInfoDetailsDomain)
            .assertComplete()

       // Mockito.verify(localDataSource, times(1))
         //   .saveFavorite(peopleIdentifier)

        Mockito.verify(remoteDataSource, times(1))
            .getPeopleInfoDetails(page)
    }

    @Test
    fun test_getPeopleInfoDetails_remote_error() {
        val page = 1
        val peopleInfoDetailsData = TestDataGenerator.generatePeopleInfoDetailsData()
        val peopleInfoDetailsDomain = PeopleInfoDetailsMapper.from(peopleInfoDetailsData)

        Mockito.`when`(remoteDataSource.getPeopleInfoDetails(page))
            .thenReturn(Observable.error(Throwable()))
        Mockito.`when`(localDataSource.getPeopleInfoDetails(page))
            .thenReturn(Observable.just(peopleInfoDetailsData))

        val testSubscriber = challengeRepository.getPeopleInfoDetails(page).test()

        testSubscriber.assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                it == peopleInfoDetailsDomain
            }
            .assertComplete()

        Mockito.verify(localDataSource, times(1))
            .getPeopleInfoDetails(page)
    }



    @Test
    fun test_getPeopleInfo_remote_error() {

        val transactionsData = TestDataGenerator.generatePeopleInfoData()

        Mockito.`when`(remoteDataSource.getPeopleInfo())
            .thenReturn(Observable.error(Throwable()))
        Mockito.`when`(localDataSource.getPeopleInfo())
            .thenReturn(Observable.just(transactionsData))

        val testSubscriber = challengeRepository.getPeopleInfo().test()

        testSubscriber.assertSubscribed()
            .assertValue { peopleInfo ->
                peopleInfo.containsAll(transactionsData.map {
                    PeopleInfoMapper.from(it)
                })
            }
            .assertComplete()

        Mockito.verify(localDataSource, times(1))
            .getPeopleInfo()
    }



}
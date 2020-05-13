package com.may.force.data.repository

import com.may.force.data.mapper.Mapper
import com.may.force.data.model.PeopleInfoData
import com.may.force.data.model.PeopleInfoDetailsData
import com.may.force.domain.entities.PeopleInfoDetailsEntity
import com.may.force.domain.entities.PeopleInfoEntity
import com.may.force.domain.repository.ChallengeRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ChallengeRepositoryImpl @Inject constructor(
    private val peopleInfoMapper: Mapper<PeopleInfoEntity, PeopleInfoData>,
    private val PeopleInfoDetailsMapper: Mapper<PeopleInfoDetailsEntity, PeopleInfoDetailsData>,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ChallengeRepository {


    override fun getPeopleInfo(): Observable<List<PeopleInfoEntity>> {


        val peopleInfoObservable = localDataSource.getPeopleInfo()
            .map { peopleInfo ->
                peopleInfo.map { peopleInfoMapper.from(it) }
            }


        return remoteDataSource.getPeopleInfo()
            .map { peopleInfo ->
                peopleInfo.map {
                    localDataSource.savePeopleInfo(it)
                    peopleInfoMapper.from(it)
                }
            }.onErrorResumeNext(Observable.empty())
            .concatWith(peopleInfoObservable)


    }

    override fun getPeopleInfoDetails(page: Int): Observable<PeopleInfoDetailsEntity> {

        val peopleInfoDetailsObservable = localDataSource.getPeopleInfoDetails(page)
            .map { PeopleInfoDetailsMapper.from(it) }

        return remoteDataSource.getPeopleInfoDetails(page)
            .map {
                PeopleInfoDetailsMapper.from(it)
            }.onErrorResumeNext(Observable.empty())
            .concatWith(peopleInfoDetailsObservable)
    }



    override fun saveFavorite(peopleInfo: PeopleInfoEntity): Completable {
        return localDataSource.saveFavorite(
            peopleInfoMapper.to(peopleInfo)
        )
    }
}
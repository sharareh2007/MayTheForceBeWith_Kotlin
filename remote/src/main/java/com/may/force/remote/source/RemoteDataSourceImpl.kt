package com.may.force.remote.source

import com.may.force.data.model.PeopleInfoData
import com.may.force.data.model.PeopleInfoDetailsData
import com.may.force.data.repository.RemoteDataSource
import com.may.force.remote.api.ChallengeService
import com.may.force.remote.mapper.Mapper
import com.may.force.remote.model.PeopleInfoDetailsNetwork
import com.may.force.remote.model.PeopleInfoNetwork
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val peopleInfoMapper: Mapper<PeopleInfoData, PeopleInfoNetwork>,
    private val peopleInfoDetailsMapper: Mapper<PeopleInfoDetailsData, PeopleInfoDetailsNetwork>,
    private val challengeService: ChallengeService
) : RemoteDataSource {

    override fun getPeopleInfo(): Observable<List<PeopleInfoData>> {
        return challengeService.getPeopleInfo()
            .map { response ->
                println("Remote Invoked")
                response.peopleInfo.map { peopleInfo: PeopleInfoNetwork ->
                    peopleInfoMapper.from(peopleInfo)
                }
            }
    }

    override fun getPeopleInfoDetails(page: Int): Observable<PeopleInfoDetailsData> {
        return challengeService.getPeopleInfoDetails(page)
            .map { response ->
                println("Remote Invoked")
                peopleInfoDetailsMapper.from(response.peopleInfoDetails)
            }
    }
}
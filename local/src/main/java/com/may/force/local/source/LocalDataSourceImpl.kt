package com.may.force.local.source

import com.may.force.data.model.PeopleInfoData
import com.may.force.data.model.PeopleInfoDetailsData
import com.may.force.data.repository.LocalDataSource
import com.may.force.local.databas.PeopleInfoDAO
import com.may.force.local.databas.PeopleInfoDetailsDAO
import com.may.force.local.mapper.PeopleInfoDataLocalMapper
import com.may.force.local.mapper.PeopleInfoDetailsDataLocalMapper
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val PeopleInfoMapper: PeopleInfoDataLocalMapper,
    private val PeopleInfoDetailsMapper: PeopleInfoDetailsDataLocalMapper,
    private val peopleInfoDAO: PeopleInfoDAO,
    private val peopleInfoDetailsDAO: PeopleInfoDetailsDAO
) : LocalDataSource {

    companion object {
        private const val page = 1
    }

    override fun getPeopleInfo(): Observable<List<PeopleInfoData>> {

        return peopleInfoDAO.getPeopleInfo()
            .map { peopleList ->
                peopleList.map {
                    println("Local Invoked")
                    PeopleInfoMapper.from(it)
                }
            }
    }

    override fun savePeopleInfo(peopleInfoData: PeopleInfoData) {
        peopleInfoDAO.savePeopleInfo(
            PeopleInfoMapper.to(peopleInfoData)
        )
    }

    override fun getPeopleInfoDetails(page: Int): Observable<PeopleInfoDetailsData> {
        return peopleInfoDetailsDAO.getPeopleDetailsInfo(page)
            .map {
                PeopleInfoDetailsMapper.from(it)
            }
    }


    override fun saveFavorite(peopleInfoData: PeopleInfoData): Completable {
        return peopleInfoDAO.saveFavorite(
            PeopleInfoMapper.to(peopleInfoData)
        )
    }
}

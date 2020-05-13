package com.may.force.data.repository

import com.may.force.data.model.PeopleInfoData
import com.may.force.data.model.PeopleInfoDetailsData
import com.may.force.domain.entities.PeopleInfoEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface LocalDataSource {

    fun getPeopleInfo(): Observable<List<PeopleInfoData>>

    fun savePeopleInfo(peopleInfoData: PeopleInfoData)

    fun getPeopleInfoDetails(page: Int): Observable<PeopleInfoDetailsData>

    fun saveFavorite(peopleInfoData: PeopleInfoData): Completable
}
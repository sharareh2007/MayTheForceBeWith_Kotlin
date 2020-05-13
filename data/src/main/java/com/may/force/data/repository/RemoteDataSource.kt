package com.may.force.data.repository

import com.may.force.data.model.PeopleInfoData
import com.may.force.data.model.PeopleInfoDetailsData
import io.reactivex.Observable


interface RemoteDataSource {

    fun getPeopleInfo(): Observable<List<PeopleInfoData>>

    fun getPeopleInfoDetails(page: Int): Observable<PeopleInfoDetailsData>
}
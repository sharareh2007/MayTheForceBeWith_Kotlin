package com.may.force.domain.repository

import com.may.force.domain.entities.PeopleInfoDetailsEntity
import com.may.force.domain.entities.PeopleInfoEntity
import io.reactivex.Completable
import io.reactivex.Observable


interface ChallengeRepository {

    fun getPeopleInfo(): Observable<List<PeopleInfoEntity>>
    fun getPeopleInfoDetails(page: Int): Observable<PeopleInfoDetailsEntity>
    fun saveFavorite(peopleInfo: PeopleInfoEntity): Completable
}
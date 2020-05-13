package com.may.force.local.databas

import androidx.room.*
import com.may.force.local.model.PeopleInfoDetailsLocal
import com.may.force.local.model.PeopleInfoLocal
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
interface PeopleInfoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun savePeopleInfo(peopleInfo: PeopleInfoLocal)

    @Query("SELECT * FROM PeopleInfoLocal ORDER BY name DESC")
    fun getPeopleInfo(): Observable<List<PeopleInfoLocal>>

    @Update
    fun saveFavorite(peopleInfo: PeopleInfoLocal): Completable

}
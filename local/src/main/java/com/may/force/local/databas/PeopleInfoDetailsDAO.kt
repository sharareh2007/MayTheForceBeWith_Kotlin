package com.may.force.local.databas

import androidx.room.*
import com.may.force.local.model.PeopleInfoDetailsLocal
import com.may.force.local.model.PeopleInfoLocal
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
interface PeopleInfoDetailsDAO {


    @Query("SELECT * FROM PeopleInfoDetailsLocal ORDER BY name DESC")
    fun getPeopleDetailsInfo(page: Int): Observable<PeopleInfoDetailsLocal>

}
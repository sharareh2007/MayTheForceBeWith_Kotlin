package com.may.force.presentation.viewModels

import androidx.lifecycle.*
import com.may.force.domain.entities.PeopleInfoEntity
import com.may.force.domain.usecases.GetPeopleInfoDetailsTask
import com.may.force.domain.usecases.GetPeopleInfoTask
import com.may.force.domain.usecases.saveFavoriteTask
import com.may.force.presentation.mapper.Mapper
import com.may.force.presentation.model.PeopleInfo
import com.may.force.presentation.model.Resource
import com.may.force.presentation.qualifier.PeopleID
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import javax.inject.Inject

class HomeVM @Inject internal constructor(
    private val peopleInfoMapper: Mapper<PeopleInfoEntity, PeopleInfo>,
    private val getPeopleInfoTask: GetPeopleInfoTask
) : ViewModel() {

    val peopleInfoResource: LiveData<Resource<List<PeopleInfo>>>
        get() = getPeopleInfoTask
            .buildUseCase()
            .map { PeopleInfoEntity ->
                PeopleInfoEntity.map {
                    peopleInfoMapper.to(it)
                }
            }.map { Resource.success(it) }
            .startWith(Resource.loading())
            .onErrorResumeNext(Function {
                Observable.just(Resource.error(it.localizedMessage))
            }).toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()

}
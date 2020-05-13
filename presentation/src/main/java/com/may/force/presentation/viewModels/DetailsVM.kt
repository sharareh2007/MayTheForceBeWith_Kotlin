package com.may.force.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.may.force.domain.entities.PeopleInfoDetailsEntity
import com.may.force.domain.entities.PeopleInfoEntity
import com.may.force.domain.usecases.GetPeopleInfoDetailsTask
import com.may.force.presentation.mapper.Mapper
import com.may.force.presentation.model.PeopleInfoDetails
import com.may.force.presentation.model.Resource
import com.may.force.presentation.qualifier.PeopleID
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class DetailsVM @Inject internal constructor(
    @PeopleID private val page: Int,
    private val peopleInfoDetailsMapper: Mapper<PeopleInfoDetailsEntity, PeopleInfoDetails>,
    private val getPeopleInfoDetailsTask: GetPeopleInfoDetailsTask
) : ViewModel() {

    val peopleInfoDetailsResource: LiveData<Resource<PeopleInfoDetails>>
        get() = getPeopleInfoDetailsTask
            .buildUseCase(GetPeopleInfoDetailsTask.Params(page))
            .map { peopleInfoDetailsMapper.to(it) }
            .map { Resource.success(it) }
            .startWith(Resource.loading())
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.error(it.localizedMessage))
                }
            ).toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
}


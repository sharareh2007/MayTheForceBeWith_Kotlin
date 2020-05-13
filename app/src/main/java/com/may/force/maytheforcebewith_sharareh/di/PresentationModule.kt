package com.may.force.maytheforcebewith_sharareh.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.may.force.domain.entities.PeopleInfoDetailsEntity
import com.may.force.domain.entities.PeopleInfoEntity
import com.may.force.presentation.factory.ViewModelFactory
import com.may.force.presentation.mapper.Mapper
import com.may.force.presentation.mapper.PeopleInfoDetailsEntityMapper
import com.may.force.presentation.mapper.PeopleInfoEntityMapper
import com.may.force.presentation.model.PeopleInfo
import com.may.force.presentation.model.PeopleInfoDetails
import com.may.force.presentation.viewModels.DetailsVM
import com.may.force.presentation.viewModels.HomeVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeVM::class)
    abstract fun bindsHomeViewModel(homeVM: HomeVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsVM::class)
    abstract fun bindsDetailsViewModel(DetailsVM: DetailsVM): ViewModel

    @Binds
    abstract fun bindsPeopleInfoMapper(
        peopleInfoEntityMapper: PeopleInfoEntityMapper
    ): Mapper<PeopleInfoEntity, PeopleInfo>

    @Binds
    abstract fun bindsPeopleInfoDetailsMapper(
        peopleInfoDetailsEntityMapper: PeopleInfoDetailsEntityMapper
    ): Mapper<PeopleInfoDetailsEntity, PeopleInfoDetails>
}
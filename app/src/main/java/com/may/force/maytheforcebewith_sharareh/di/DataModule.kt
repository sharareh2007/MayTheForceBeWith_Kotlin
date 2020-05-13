package com.may.force.maytheforcebewith_sharareh.di

import com.may.force.data.mapper.Mapper
import com.may.force.data.mapper.PeopleInfoDetailsDomainDataMapper
import com.may.force.data.mapper.PeopleInfoDomainDataMapper
import com.may.force.data.model.PeopleInfoData
import com.may.force.data.model.PeopleInfoDetailsData
import com.may.force.data.repository.ChallengeRepositoryImpl
import com.may.force.domain.entities.PeopleInfoDetailsEntity
import com.may.force.domain.entities.PeopleInfoEntity
import com.may.force.domain.repository.ChallengeRepository
import dagger.Binds
import dagger.Module


@Module
abstract class DataModule {

    @Binds
    abstract fun bindsRepository(
        repoImpl: ChallengeRepositoryImpl
    ): ChallengeRepository

    @Binds
    abstract fun bindsPeopleMapper(
        mapper: PeopleInfoDomainDataMapper
    ): Mapper<PeopleInfoEntity, PeopleInfoData>

    @Binds
    abstract fun bindsPeopleInfoDetailsMapper(
        mapper: PeopleInfoDetailsDomainDataMapper
    ): Mapper<PeopleInfoDetailsEntity, PeopleInfoDetailsData>
}
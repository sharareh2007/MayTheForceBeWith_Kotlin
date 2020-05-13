package com.may.force.maytheforcebewith_sharareh.di

import android.app.Application
import com.may.force.data.model.PeopleInfoData
import com.may.force.data.model.PeopleInfoDetailsData
import com.may.force.data.repository.LocalDataSource
import com.may.force.local.databas.ChallengeDB
import com.may.force.local.mapper.Mapper
import com.may.force.local.mapper.PeopleInfoDataLocalMapper
import com.may.force.local.mapper.PeopleInfoDetailsDataLocalMapper
import com.may.force.local.model.PeopleInfoDetailsLocal
import com.may.force.local.model.PeopleInfoLocal
import com.may.force.local.source.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [LocalPersistenceModule.Binders::class])
class LocalPersistenceModule {

    @Module
    interface Binders {

        @Binds
        fun bindsLocalDataSource(
            localDataSourceImpl: LocalDataSourceImpl
        ): LocalDataSource

        @Binds
        fun bindUserInfoMapper(
            peopleInfoMapper: PeopleInfoDataLocalMapper
        ): Mapper<PeopleInfoData, PeopleInfoLocal>

        @Binds
        fun bindTransactionMapper(
            peopleInfoDetailsMapper: PeopleInfoDetailsDataLocalMapper
        ): Mapper<PeopleInfoDetailsData, PeopleInfoDetailsLocal>
    }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application
    ) = ChallengeDB.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesPeopleInfoDAO(
        challengeDB: ChallengeDB
    ) = challengeDB.getPeopleInfoDao()

    @Provides
    @Singleton
    fun providesPeopleInfoDetailsDAO(
        challengeDB: ChallengeDB
    ) = challengeDB.getPeopleInfoDetailsDao()

}

package com.may.force.maytheforcebewith_sharareh.di

import com.may.force.data.model.PeopleInfoData
import com.may.force.data.model.PeopleInfoDetailsData
import com.may.force.data.repository.RemoteDataSource
import com.may.force.remote.api.ChallengeService
import com.may.force.remote.mapper.Mapper
import com.may.force.remote.mapper.PeopleInfoDataNetworkMapper
import com.may.force.remote.mapper.PeopleInfoDetailsDataNetworkMapper
import com.may.force.remote.model.PeopleInfoDetailsNetwork
import com.may.force.remote.model.PeopleInfoNetwork
import com.may.force.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module(includes = [RemoteModule.Binders::class])
class RemoteModule {

    @Module
    interface Binders {

        @Binds
        fun bindsRemoteSource(
            remoteDataSourceImpl: RemoteDataSourceImpl
        ): RemoteDataSource

        @Binds
        fun bindPeopleInfoMapper(
            peopleInfoMapper: PeopleInfoDataNetworkMapper
        ): Mapper<PeopleInfoData, PeopleInfoNetwork>

        @Binds
        fun bindPeopleInfoDetailsMapper(
            peopleInfoDetailsMapper: PeopleInfoDetailsDataNetworkMapper
        ): Mapper<PeopleInfoDetailsData, PeopleInfoDetailsNetwork>
    }

    @Provides
    fun providesChallengeService(retrofit: Retrofit): ChallengeService =
        retrofit.create(ChallengeService::class.java)


    @Provides
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()


}
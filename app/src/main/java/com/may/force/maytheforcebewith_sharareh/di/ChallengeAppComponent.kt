package com.may.force.maytheforcebewith_sharareh.di

import android.app.Application
import com.may.force.maytheforcebewith_sharareh.application.ChallengeApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DomainModule::class,
        DataModule::class,
        LocalPersistenceModule::class,
        RemoteModule::class,
        PresentationModule::class,
        AppModule::class
    ]
)
interface ChallengeAppComponent : AndroidInjector<ChallengeApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): ChallengeAppComponent
    }

    override fun inject(app: ChallengeApp)
}
package com.may.force.maytheforcebewith_sharareh.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun contributesDetailsActivity(): DetailsActivity

}
package com.may.force.maytheforcebewith_sharareh.application

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class ChallengeApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerBankBuddyAppComponent.builder().application(this).build()
    }
}
package com.jphernandez.intermediachallenge.application

import android.app.Application

class IntermediaChallengeApplication: Application() {

    companion object {
        val appComponent: AppComponent =
            DaggerAppComponent.create()
    }
}
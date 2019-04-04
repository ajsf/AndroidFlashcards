package com.doublea.androidflashcards

import android.app.Application
import com.doublea.androidflashcards.di.AppComponent
import com.doublea.androidflashcards.di.AppModule
import com.doublea.androidflashcards.di.DaggerAppComponent

open class MainApplication : Application() {

    open val component: AppComponent =
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
}
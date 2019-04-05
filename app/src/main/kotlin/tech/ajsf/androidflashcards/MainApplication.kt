package tech.ajsf.androidflashcards

import android.app.Application
import tech.ajsf.androidflashcards.di.AppComponent
import tech.ajsf.androidflashcards.di.AppModule
import tech.ajsf.androidflashcards.di.DaggerAppComponent

open class MainApplication : Application() {

    open val component: AppComponent =
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
}
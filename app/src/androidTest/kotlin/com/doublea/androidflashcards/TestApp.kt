package com.doublea.androidflashcards

import com.doublea.androidflashcards.db.DaggerTestComponent
import com.doublea.androidflashcards.di.AppComponent
import com.doublea.androidflashcards.di.AppModule

class TestApp : MainApplication() {

    override val component: AppComponent = DaggerTestComponent
            .builder()
            .appModule(AppModule(this))
            .build()
}
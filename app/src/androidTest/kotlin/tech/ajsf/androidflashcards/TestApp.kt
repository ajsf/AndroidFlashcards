package tech.ajsf.androidflashcards

import tech.ajsf.androidflashcards.db.DaggerTestComponent
import tech.ajsf.androidflashcards.di.AppComponent
import tech.ajsf.androidflashcards.di.AppModule

class TestApp : MainApplication() {

    override val component: AppComponent = DaggerTestComponent
            .builder()
            .appModule(AppModule(this))
            .build()
}
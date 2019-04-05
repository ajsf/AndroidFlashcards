package tech.ajsf.androidflashcards.di

import tech.ajsf.androidflashcards.FlashcardActivity
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = [DbModule::class]) interface AppComponent {
    fun inject(into: FlashcardActivity)
}


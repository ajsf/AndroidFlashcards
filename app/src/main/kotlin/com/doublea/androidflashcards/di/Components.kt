package com.doublea.androidflashcards.di

import com.doublea.androidflashcards.FlashcardActivity
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(DbModule::class)) interface AppComponent {
    fun inject(into: FlashcardActivity)
}


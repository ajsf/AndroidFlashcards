package com.doublea.androidflashcards.db

import android.arch.persistence.room.Room
import android.content.Context
import com.doublea.androidflashcards.di.AppComponent
import com.doublea.androidflashcards.di.AppModule
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(AppModule::class)) class InMemoryDbModule {

    @Provides @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).addCallback(TestDbCallback()).build()
}

@Singleton @Component(modules = arrayOf(InMemoryDbModule::class)) interface TestComponent : AppComponent
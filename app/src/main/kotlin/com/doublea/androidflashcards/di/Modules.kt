package com.doublea.androidflashcards.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.doublea.androidflashcards.db.AppDatabase
import com.doublea.androidflashcards.db.DbCallback
import com.doublea.androidflashcards.db.FlashcardDao
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.repository.FlashcardRepository
import com.doublea.androidflashcards.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class AppModule(private val app: Application) {

    @Provides @Singleton
    fun provideContext(): Context = app

    @Provides @Singleton
    fun provideFlashcardDao(database: AppDatabase) = database.flashcardDao

    @Provides @Singleton
    fun provideRepository(flashcardDao: FlashcardDao): Repository<Flashcard> = FlashcardRepository(flashcardDao)

}

@Module(includes = arrayOf(AppModule::class)) class DbModule {

    @Provides @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "flashcards.db").addCallback(DbCallback()).build()
}





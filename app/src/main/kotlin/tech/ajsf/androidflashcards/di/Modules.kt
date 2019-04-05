package tech.ajsf.androidflashcards.di

import android.app.Application
import android.content.Context
import tech.ajsf.androidflashcards.db.AppDatabase
import tech.ajsf.androidflashcards.db.FlashcardDao
import tech.ajsf.androidflashcards.model.Flashcard
import tech.ajsf.androidflashcards.repository.FlashcardRepository
import tech.ajsf.androidflashcards.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideFlashcardDao(database: AppDatabase) = database.flashcardDao

    @Provides
    @Singleton
    fun provideRepository(flashcardDao: FlashcardDao): Repository<Flashcard> =
        FlashcardRepository(flashcardDao)

}

@Module(includes = [AppModule::class])
class DbModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)
}





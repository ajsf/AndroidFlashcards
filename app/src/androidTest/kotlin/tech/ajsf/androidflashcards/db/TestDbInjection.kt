package tech.ajsf.androidflashcards.db

import android.arch.persistence.room.Room
import android.content.Context
import tech.ajsf.androidflashcards.di.AppComponent
import tech.ajsf.androidflashcards.di.AppModule
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class InMemoryDbModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = Room
        .inMemoryDatabaseBuilder(context, AppDatabase::class.java)
        .addCallback(TestDbCallback(context)).build()
}

@Singleton
@Component(modules = [InMemoryDbModule::class])
interface TestComponent : AppComponent
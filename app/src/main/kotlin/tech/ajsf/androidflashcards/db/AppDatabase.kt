package tech.ajsf.androidflashcards.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [FlashcardEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val flashcardDao: FlashcardDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "flashcards.db"
        ).build()
    }
}

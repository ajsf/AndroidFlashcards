package tech.ajsf.androidflashcards.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import tech.ajsf.androidflashcards.model.Flashcard
import kotlin.concurrent.thread

@Database(entities = [Flashcard::class], version = 1, exportSchema = false)
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
        ).addCallback(object : Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val dao = getInstance(context).flashcardDao

                val items = allFlashcards()

                thread { dao.insertAll(items) }
            }
        }).build()
    }
}

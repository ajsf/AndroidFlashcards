package com.doublea.androidflashcards.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.doublea.androidflashcards.model.Flashcard
import kotlin.concurrent.thread

class TestDbCallback(val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        val cards = listOf(
            Flashcard("question", "answer", "category"),
            Flashcard("question2", "answer2", "category2")
        )

        val dao = AppDatabase.getInstance(context).flashcardDao

        thread {
            dao.insertAll(cards)
        }
    }
}
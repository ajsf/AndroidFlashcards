package com.doublea.androidflashcards.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.RoomDatabase
import android.content.ContentValues
import com.doublea.androidflashcards.model.Flashcard

@Database(entities = [Flashcard::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val flashcardDao: FlashcardDao
}

class DbCallback : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {

        fun insertItem(question: String, answer: String) {
            val contentValues = ContentValues()
            contentValues.put("question", question)
            contentValues.put("answer", answer)
            db.insert("flashcard", OnConflictStrategy.REPLACE, contentValues)
        }

        dataStructureAndAlgorithms.forEach { insertItem(it.key, it.value) }
        coreJava.forEach { insertItem(it.key, it.value) }
    }
}
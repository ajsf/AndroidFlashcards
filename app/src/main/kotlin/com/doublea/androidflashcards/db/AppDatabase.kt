package com.doublea.androidflashcards.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.RoomDatabase
import android.content.ContentValues
import com.doublea.androidflashcards.model.Flashcard

@Database(entities = arrayOf(Flashcard::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val flashcardDao: FlashcardDao
}

class DbCallback : RoomDatabase.Callback() {

    val contentValues = ContentValues()
    lateinit var db: SupportSQLiteDatabase

    override fun onCreate(db: SupportSQLiteDatabase) {
        this.db = db
        insertItemsIntoDb(dataStructureAndAlgorithms)
        insertItemsIntoDb(coreJava)
    }

    fun insertItemsIntoDb(dataMap: Map<String, String>) = dataMap.forEach { insertItem(it.key, it.value) }

    fun insertItem(question: String, answer: String) {
        contentValues.put("question", question)
        contentValues.put("answer", answer)
        db.insert("flashcard", OnConflictStrategy.REPLACE, contentValues)
    }
}
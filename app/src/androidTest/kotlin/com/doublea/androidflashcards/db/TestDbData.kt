package com.doublea.androidflashcards.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.RoomDatabase
import android.content.ContentValues

class TestDbCallback : RoomDatabase.Callback() {

    val contentValues = ContentValues()
    lateinit var db: SupportSQLiteDatabase

    override fun onCreate(db: SupportSQLiteDatabase) {
        this.db = db
        contentValues.put("question", "question")
        contentValues.put("answer", "answer")
        db.insert("flashcard", OnConflictStrategy.REPLACE, contentValues)
        contentValues.put("question", "question2")
        contentValues.put("answer", "answer2")
        db.insert("flashcard", OnConflictStrategy.REPLACE, contentValues)
    }
}
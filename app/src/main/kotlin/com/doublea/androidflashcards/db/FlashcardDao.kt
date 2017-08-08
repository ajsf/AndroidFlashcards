package com.doublea.androidflashcards.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.doublea.androidflashcards.model.Flashcard

@Dao interface FlashcardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(flashcard: Flashcard)

    @Update
    fun update(flashcard: Flashcard)

    @Query("SELECT * FROM flashcard")
    fun getFlashcards(): LiveData<List<Flashcard>>
}
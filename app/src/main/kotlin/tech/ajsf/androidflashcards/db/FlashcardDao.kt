package tech.ajsf.androidflashcards.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import tech.ajsf.androidflashcards.model.Flashcard

@Dao
interface FlashcardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(flashcard: Flashcard)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(flashcards: List<Flashcard>)

    @Update
    fun update(flashcard: Flashcard)

    @Query("SELECT * FROM flashcard")
    fun getFlashcards(): LiveData<List<Flashcard>>
}
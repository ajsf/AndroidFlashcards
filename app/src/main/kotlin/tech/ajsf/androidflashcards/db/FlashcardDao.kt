package tech.ajsf.androidflashcards.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface FlashcardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(flashcard: FlashcardEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(flashcards: List<FlashcardEntity>)

    @Update
    fun update(flashcard: FlashcardEntity)

    @Query("SELECT * FROM flashcard")
    fun getFlashcards(): LiveData<List<FlashcardEntity>>
}
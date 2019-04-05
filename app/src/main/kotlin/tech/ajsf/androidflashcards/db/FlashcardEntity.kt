package tech.ajsf.androidflashcards.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "flashcard")
data class FlashcardEntity(
    @PrimaryKey val question: String, val answer: String, val category: String
)
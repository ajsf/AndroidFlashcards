package com.doublea.androidflashcards.repository

import android.arch.lifecycle.LiveData
import com.doublea.androidflashcards.db.FlashcardDao
import com.doublea.androidflashcards.model.Flashcard
import javax.inject.Inject
import javax.inject.Singleton

interface Repository<T> {
    fun loadData(): LiveData<List<T>>
    fun updateItem(item: T)
}

@Singleton
class FlashcardRepository @Inject constructor(private val flashcardDao: FlashcardDao) : Repository<Flashcard> {

    override fun loadData(): LiveData<List<Flashcard>> = flashcardDao.getFlashcards()

    override fun updateItem(item: Flashcard) {
        flashcardDao.update(item)
    }
}
package tech.ajsf.androidflashcards.repository

import android.arch.lifecycle.LiveData
import tech.ajsf.androidflashcards.db.FlashcardDao
import tech.ajsf.androidflashcards.model.Flashcard
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
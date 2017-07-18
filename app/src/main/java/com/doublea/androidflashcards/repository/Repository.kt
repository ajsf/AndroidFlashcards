package com.doublea.androidflashcards.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.doublea.androidflashcards.model.Flashcard

interface Repository<T> {
    fun loadData(): LiveData<List<T>>
}

class FlashcardRepository : Repository<Flashcard> {

    override fun loadData(): LiveData<List<Flashcard>> {
        return getFlashcards()
    }

    private fun getFlashcards(): LiveData<List<Flashcard>> {
        val data = MutableLiveData<List<Flashcard>>()
        val flashcards = listOf(Flashcard("Test"), Flashcard("Does this work?"))
        data.value = flashcards
        return data
    }
}
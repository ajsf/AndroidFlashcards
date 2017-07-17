package com.doublea.androidflashcards.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.repository.FlashcardRepository

class FlashcardViewModel : ViewModel() {

    private var flashcards: MutableLiveData<List<Flashcard>> = MutableLiveData()
    val selectedFlashcard = MutableLiveData<Flashcard>()

    private val repository = FlashcardRepository()

    fun getModelData(): MutableLiveData<List<Flashcard>> {
        if (flashcards.value == null) {
            flashcards = repository.loadData() as MutableLiveData<List<Flashcard>>
        }
            return flashcards
    }

    fun select(flashcard: Flashcard) {
        selectedFlashcard.value = flashcard
    }

    companion object {
        fun create(activity: FragmentActivity) : FlashcardViewModel {
            return ViewModelProviders.of(activity).get(FlashcardViewModel::class.java)
        }
    }
}
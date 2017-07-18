package com.doublea.androidflashcards.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.repository.FlashcardRepository
import com.doublea.androidflashcards.repository.Repository

class FlashcardViewModel(var repository: Repository<Flashcard>) : ViewModel() {

    constructor() : this(FlashcardRepository())

    private var flashcards: LiveData<List<Flashcard>> = MutableLiveData()
    var selectedFlashcard = MutableLiveData<Flashcard>()

    fun getModelData(): LiveData<List<Flashcard>> {
        if (flashcards.value == null) {
            flashcards = repository.loadData() as MutableLiveData<List<Flashcard>>
        }
        return flashcards
    }

    fun select(flashcard: Flashcard) {
        selectedFlashcard.value = flashcard
    }

    companion object {
        fun create(activity: FragmentActivity): FlashcardViewModel {
            return ViewModelProviders.of(activity).get(FlashcardViewModel::class.java)
        }
    }
}
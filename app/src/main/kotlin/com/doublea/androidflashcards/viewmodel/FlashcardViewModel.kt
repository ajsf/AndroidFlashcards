package com.doublea.androidflashcards.viewmodel

import android.arch.lifecycle.*
import android.support.v4.app.FragmentActivity
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.repository.Repository

class FlashcardViewModel : ViewModel() {

    val flashcards: LiveData<List<Flashcard>> by lazy {
        repository.loadData()
    }

    val selectedFlashcard = MutableLiveData<Flashcard>()
    val showAnswer = MutableLiveData<Boolean>()
    var editedText: String? = null

    lateinit var repository: Repository<Flashcard>

    fun select(flashcard: Flashcard) {
        selectedFlashcard.value = flashcard
        showAnswer.value = false
        editedText = null
    }

    fun updateSelectedFlashcard(newAnswer: String) {
        val flashcard = selectedFlashcard.value?.copy(answer = newAnswer)
        if (flashcard != null) {
            repository.updateItem(flashcard)
            select(flashcard)
        }
    }

    companion object {
        fun create(
            activity: FragmentActivity,
            viewModelFactory: ViewModelProvider.Factory? = null
        ): FlashcardViewModel {
            if (viewModelFactory == null) {
                return ViewModelProviders.of(activity).get(FlashcardViewModel::class.java)
            }
            return ViewModelProviders.of(activity, viewModelFactory)
                .get(FlashcardViewModel::class.java)
        }
    }
}
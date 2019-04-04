package com.doublea.androidflashcards.viewmodel

import android.arch.lifecycle.*
import android.support.v4.app.FragmentActivity
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.repository.Repository

data class QuizViewState(
    val selectedFlashcard: Flashcard,
    val showAnswer: Boolean = false
)

class FlashcardViewModel : ViewModel() {

    val quizViewStateLiveData: LiveData<QuizViewState>
        get() = _viewStateLiveData

    private val _viewStateLiveData: MutableLiveData<QuizViewState> = MutableLiveData()

    val flashcards: LiveData<List<Flashcard>> by lazy {
        repository.loadData()
    }

    var editedText: String? = null

    lateinit var repository: Repository<Flashcard>

    fun select(flashcard: Flashcard) {
        val newViewState = QuizViewState(flashcard)
        _viewStateLiveData.postValue(newViewState)
        editedText = null
    }

    fun showAnswer() {
        val newViewState = getViewState().copy(showAnswer = true)
        _viewStateLiveData.postValue(newViewState)
    }

    fun updateSelectedFlashcard(newAnswer: String) {
        val newFlashcard = getViewState().selectedFlashcard.copy(answer = newAnswer)
        repository.updateItem(newFlashcard)
        select(newFlashcard)
    }

    private fun getViewState(): QuizViewState = quizViewStateLiveData.value!!

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
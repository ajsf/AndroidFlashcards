package com.doublea.androidflashcards.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.repository.Repository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule

class FlashcardViewModelTest {

    lateinit var mockRepository: Repository<*>
    lateinit var vm: FlashcardViewModel
    lateinit var flashcardList: List<Flashcard>

    @Rule @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        mockRepository = mock(Repository::class.java)
        val data = MutableLiveData<List<Flashcard>>()
        val flashcards = listOf(Flashcard("Test"), Flashcard("Does this work?"), Flashcard("3rd Item"))
        data.value = flashcards
        `when`(mockRepository.loadData()).thenReturn(data)
        vm = FlashcardViewModel(mockRepository as Repository<Flashcard>)
        flashcardList = vm.getModelData().value!!
    }

    @Test
    fun itRetrievesAllOfTheFlashcardsFromTheRepository() {
        assertThat(flashcardList.size, `is`(3))
    }

    @Test
    fun itCanSelectOneFlashcard() {
        vm.select(flashcardList[0])
        var question = vm.selectedFlashcard.value?.question
        assertThat(question, `is`("Test"))
        vm.select(flashcardList[1])
        question = vm.selectedFlashcard.value?.question
        assertThat(question, `is`("Does this work?"))
    }
}
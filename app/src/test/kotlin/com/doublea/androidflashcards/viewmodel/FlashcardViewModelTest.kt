package com.doublea.androidflashcards.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.doublea.androidflashcards.dummyFlashcardList
import com.doublea.androidflashcards.flashcard1
import com.doublea.androidflashcards.flashcard2
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.repository.Repository
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FlashcardViewModelTest {

    @Mock
    lateinit var mockRepository: Repository<Flashcard>

    lateinit var vm: FlashcardViewModel
    lateinit var flashcardList: List<Flashcard>

    @Rule @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val data = MutableLiveData<List<Flashcard>>()
        data.value = dummyFlashcardList
        `when`(mockRepository.loadData()).thenReturn(data)
        vm = FlashcardViewModel()
        vm.repository = mockRepository
        flashcardList = vm.flashcards.value!!
    }

    @Test
    fun itRetrievesAllOfTheFlashcardsFromTheRepository() {
        assertThat(flashcardList.size, `is`(3))
    }

    @Test
    fun `repository is only accessed the first time getFlashcards is called`() {
        verify(mockRepository, times(1)).loadData()
        vm.flashcards
        vm.flashcards
        vm.flashcards
        verify(mockRepository, times(1)).loadData()
    }

    @Test
    fun itCanSelectOneFlashcard() {
        vm.select(flashcardList[0])
        var question = vm.selectedFlashcard.value?.question
        assertThat(question, `is`(flashcard1.question))
        vm.select(flashcardList[1])
        question = vm.selectedFlashcard.value?.question
        assertThat(question, `is`(flashcard2.question))
    }

    @Test
    fun whenFlashcardIsSelectedShowAnswerIsSetToFalse() {
        vm.showAnswer.value = true
        vm.select(flashcardList[0])
        assertThat(vm.showAnswer.value, `is`(false))
    }

    @Test
    fun whenFlashcardIsSelectedEditedTextIsSetToNull() {
        vm.editedText = "Hello"
        assertThat(vm.editedText, `is`("Hello"))
        vm.select(flashcardList[0])
        assertNull(vm.editedText)
    }

    @Test
    fun itUpdatesTheSelectedFlashcard() {
        vm.select(flashcardList[0])
        vm.updateSelectedFlashcard("New answer")
        verify(mockRepository).updateItem(dummyFlashcardList[0].copy(answer = "New answer"))
    }
}
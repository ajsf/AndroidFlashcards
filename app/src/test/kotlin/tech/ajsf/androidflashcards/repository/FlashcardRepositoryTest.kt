package tech.ajsf.androidflashcards.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import tech.ajsf.androidflashcards.db.FlashcardDao
import tech.ajsf.androidflashcards.dummyFlashcardList
import tech.ajsf.androidflashcards.flashcard1
import tech.ajsf.androidflashcards.model.Flashcard
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class FlashcardRepositoryTest {

    lateinit var flashcardDao: FlashcardDao
    lateinit var data: MutableLiveData<List<Flashcard>>

    @Rule @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        flashcardDao = Mockito.mock(FlashcardDao::class.java)
        data = MutableLiveData<List<Flashcard>>()
        data.value = dummyFlashcardList
    }

    @Test
    fun `it returns the flashcards from the DAO`() {
        val repo = FlashcardRepository(flashcardDao)
        `when`(flashcardDao.getFlashcards()).thenReturn(data)
        val returnedFlashcards = repo.loadData()
        assertThat(returnedFlashcards, `is`(data as LiveData<List<Flashcard>>))
    }

    @Test
    fun `it updates flashcards in the DAO`() {
        val repo = FlashcardRepository(flashcardDao)
        repo.updateItem(flashcard1)
        verify(flashcardDao).update(flashcard1)
    }
}


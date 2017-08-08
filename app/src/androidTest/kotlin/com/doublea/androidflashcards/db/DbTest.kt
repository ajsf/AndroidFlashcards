package com.doublea.androidflashcards.db

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.doublea.androidflashcards.model.Flashcard
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class) class DbTest {

    lateinit var dao: FlashcardDao
    lateinit var db: AppDatabase
    lateinit var flashcardLiveData: LiveData<List<Flashcard>>

    val flashcard1 = Flashcard("flashcard1", "1 answer")
    val flashcard2 = Flashcard("flashcard2", "2 answer")
    val flashcardList = listOf(flashcard1, flashcard2)

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.flashcardDao
        dao.insert(flashcard1)
        dao.insert(flashcard2)
        flashcardLiveData = dao.getFlashcards().blockingObserve()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun itSavesAndReturnsFlashcards() {
        val flashcards = flashcardLiveData.value ?: emptyList()
        assertThat(flashcards, `is`(flashcardList))
    }

    @Test
    fun itUpdatesExistingFlashcards() {
        val flashcard = flashcardLiveData.value?.get(0)
        flashcard?.answer = "A different answer"
        dao.update(flashcard!!)
        flashcardLiveData = dao.getFlashcards().blockingObserve()
        val flashcards = flashcardLiveData.value ?: emptyList()
        assertThat(flashcards.size, `is`(2))
        assertThat(flashcards[0], `is`(flashcard))
        assertThat(flashcards[1], `is`(flashcard2))
    }

    @Test
    fun updateDoesNotWorkForNewFlashcards() {
        val flashcard = Flashcard("A new question?", "A new answer")
        dao.update(flashcard)
        flashcardLiveData = dao.getFlashcards().blockingObserve()
        assertThat(flashcardLiveData.value, `is`(flashcardList))
    }

    @Test
    fun insertWorksForNewFlashcards() {
        val newFlashcard = Flashcard("A new question?", "A new answer")
        dao.insert(newFlashcard)
        flashcardLiveData = dao.getFlashcards().blockingObserve()
        val flashcards = flashcardLiveData.value ?: emptyList()
        assertThat(flashcards.size, `is`(3))
        assertThat(flashcards.subList(0, 2), `is`(flashcardList))
        assertThat(flashcards[2], `is`(newFlashcard))
    }
}

fun <T> LiveData<T>.blockingObserve(): LiveData<T> {
    val latch = CountDownLatch(1)
    val innerObserver = Observer<T> {
        latch.countDown()
    }
    observeForever(innerObserver)
    latch.await(2, TimeUnit.SECONDS)
    return this
}
package com.doublea.androidflashcards.ui

import com.doublea.androidflashcards.model.Flashcard
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FlashcardAdapterTest {

    @Test
    fun itStartsWithAnEmptyFlashcardList() {
        val adapter = FlashcardAdapter { }
        assertThat(adapter.dataSource, `is`(emptyList<Flashcard>()))
        assertThat(adapter.itemCount, `is`(0))
    }
}
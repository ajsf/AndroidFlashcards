package com.doublea.androidflashcards.ui

import android.support.test.runner.AndroidJUnit4
import com.doublea.androidflashcards.model.Flashcard
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlashcardAdapterTest {

    @Test
    fun itStartsWithAnEmptyFlashcardList() {
        val adapter = FlashcardAdapter {}
        assertThat(adapter.dataSource, `is`(emptyList<Flashcard>()))
        assertThat(adapter.itemCount, `is`(0))
    }
}
package com.doublea.androidflashcards.model

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class FlashcardTest {

    @Test
    fun testCreateFlashcardWithQuestionOnly() {
        val flashcard = Flashcard("Test")
        assertThat(flashcard.question, `is`("Test"))
        assertThat(flashcard.answer, `is`(defaultAnswer))
        assertThat(flashcard.links, `is`(defaultLinks))
    }
}
package tech.ajsf.androidflashcards.model

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class FlashcardTest {

    @Test
    fun `a question and an answer is required to create a flashcard`() {
        val flashcard = Flashcard("A question", "An answer", "A Category")
        assertThat(flashcard.question, `is`("A question"))
        assertThat(flashcard.answer, `is`("An answer"))
    }

    @Test
    fun `when an empty string is passed for the answer, the default answer is used`() {
        val flashcard = Flashcard("A question", "", "")
        assertThat(flashcard.answer, `is`(defaultAnswer))
    }

}
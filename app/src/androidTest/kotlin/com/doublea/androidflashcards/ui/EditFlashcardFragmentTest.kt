package com.doublea.androidflashcards.ui

import android.arch.lifecycle.ViewModelProviders
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import com.doublea.androidflashcards.R
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.viewmodel.FlashcardViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditFlashcardFragmentTest {

    val question = "Test question"
    val answer = "Test answer"
    val category = "Test Category"

    @Rule
    @JvmField
    val fragmentTestRule = FlashcardFragmentTestRule(EditFlashcardFragment::class.java)

    @Before
    fun setup() {
        val vm =
            ViewModelProviders.of(fragmentTestRule.activity).get(FlashcardViewModel::class.java)
        fragmentTestRule.activity.runOnUiThread { vm.select(Flashcard(question, answer, category)) }
    }

    @Test
    fun questionTextViewIsDisplayedWithQuestion() {
        onView(withId(R.id.edit_question_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.edit_question_tv)).check(matches(withText(question)))
    }

    @Test
    fun answerEditTextIsDisplayedWithAnswer() {
        onView(withId(R.id.answer_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.answer_edit_text)).check(matches(withText(answer)))
    }

    @Test
    fun clearButtonClearsAnswerEditText() {
        onView(withId(R.id.action_clear)).perform(click())
        onView(withId(R.id.answer_edit_text)).check(matches(withText("")))
    }

    @Test
    fun resetButtonRestoresAnswerToOriginalState() {
        onView(withId(R.id.action_clear)).perform(click())
        onView(withId(R.id.action_reset)).perform(click())
        onView(withId(R.id.answer_edit_text)).check(matches(withText(answer)))
    }
}
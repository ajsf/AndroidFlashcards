package com.doublea.androidflashcards.ui

import android.arch.lifecycle.ViewModelProviders
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import com.doublea.androidflashcards.R
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.model.defaultLinks
import com.doublea.androidflashcards.viewmodel.FlashcardViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlashcardQuizFragmentTest {

    @Rule
    @JvmField
    val fragmentTestRule = FlashcardFragmentTestRule(FlashcardQuizFragment::class.java)

    @Before
    fun setup() {
        val vm =
            ViewModelProviders.of(fragmentTestRule.activity).get(FlashcardViewModel::class.java)
        fragmentTestRule.activity.runOnUiThread {
            vm.select(
                Flashcard(
                    "Test",
                    "Answer",
                    "Category"
                )
            )
        }
    }

    @Test
    fun editButton_ViewAnswerButton_And_QuestionTextView_AreDisplayed() {
        onView(withId(R.id.action_edit)).check(matches(isDisplayed()))
        onView(withId(R.id.action_view_answer)).check(matches(isDisplayed()))
        onView(withId(R.id.quiz_question_tv)).check(matches(withText("Test")))
    }

    @Test
    fun whenFirstLaunchedTheAnswerIsInvisible() {
        onView(withId(R.id.flashcard_answer)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
    }

    @Test
    fun afterClickingDisplayAnswer_AnswerIsVisible() {
        onView(withId(R.id.action_view_answer)).perform(click())
        onView(withId(R.id.flashcard_answer)).check(matches(isDisplayed()))
        onView(withId(R.id.answer_tv)).check(matches(withText("Answer")))
        onView(withText(defaultLinks[0].toString())).check(matches(isDisplayed()))
    }

    @Test
    fun afterClickingDisplayAnswer_ActionViewAnswerIsGone() {
        onView(withId(R.id.action_view_answer)).perform(click())
        onView(withId(R.id.action_view_answer)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun clickingEditButtonDisplaysEditFragment() {
        onView(withId(R.id.action_edit)).perform(click())
        onView(withId(R.id.answer_edit_text)).check(matches(isDisplayed()))
    }
}
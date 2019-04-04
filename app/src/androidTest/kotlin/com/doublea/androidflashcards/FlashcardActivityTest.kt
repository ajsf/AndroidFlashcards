package com.doublea.androidflashcards

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlashcardActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<FlashcardActivity>(FlashcardActivity::class.java)

    @Test
    fun flashcardListIsDisplayed() {
        onView(withId(R.id.flashcard_list)).check(matches(isDisplayed()))
    }
}
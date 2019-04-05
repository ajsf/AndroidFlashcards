package tech.ajsf.androidflashcards.ui

import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlashcardListFragmentTest {

    @Rule @JvmField
    val fragmentRule = FlashcardFragmentTestRule(FlashcardListFragment::class.java)

    @Test
    fun testDataIsDisplayed() {
    }
}
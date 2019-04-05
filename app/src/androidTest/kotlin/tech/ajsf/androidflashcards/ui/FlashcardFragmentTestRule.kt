package tech.ajsf.androidflashcards.ui

import android.support.test.rule.ActivityTestRule
import tech.ajsf.androidflashcards.FlashcardActivity
import tech.ajsf.androidflashcards.R

class FlashcardFragmentTestRule<F : FlashcardBaseFragment>(fragmentClass: Class<F>) : ActivityTestRule<FlashcardActivity>(FlashcardActivity::class.java) {

    val fragment = fragmentClass.newInstance() as FlashcardBaseFragment

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()
        activity.runOnUiThread {
            val manager = activity.supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}
package tech.ajsf.androidflashcards.extensions

import android.os.Build
import android.support.transition.ChangeBounds
import android.support.transition.Fade
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.ajsf.androidflashcards.R

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun Fragment.launchFragment(
    fm: FragmentManager,
    addToBackStack: Boolean = true,
    tag: String = "TAG",
    sharedView: View? = null
) {

    this.sharedElementEnterTransition = ChangeBounds().apply { duration = 800 }
    this.returnTransition = Fade().apply {
        duration = 400
    }
    this.allowReturnTransitionOverlap = false
    allowEnterTransitionOverlap = false

    val ft = fm.beginTransaction()
    ft.replace(R.id.fragment_container, this, tag)
    sharedView?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            it.transitionName = "question"
            ft.addSharedElement(it, "question")
        }
    }
    if (addToBackStack) {
        ft.addToBackStack(null)
    }

    ft.commit()
}
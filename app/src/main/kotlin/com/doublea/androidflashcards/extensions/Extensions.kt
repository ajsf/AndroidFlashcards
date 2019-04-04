package com.doublea.androidflashcards.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doublea.androidflashcards.R

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun Fragment.launchFragment(
    fm: FragmentManager,
    addToBackStack: Boolean = true,
    tag: String = "TAG"
) {
    val ft = fm.beginTransaction()
    ft.replace(R.id.fragment_container, this, tag)
    if (addToBackStack) {
        ft.addToBackStack(null)
    }
    ft.commit()
}
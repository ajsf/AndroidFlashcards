package com.doublea.androidflashcards.ui

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import com.doublea.androidflashcards.viewmodel.FlashcardViewModel

abstract class FlashcardBaseFragment : LifecycleFragment() {

    lateinit var viewModel: FlashcardViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(activity).get(FlashcardViewModel::class.java)
    }
}
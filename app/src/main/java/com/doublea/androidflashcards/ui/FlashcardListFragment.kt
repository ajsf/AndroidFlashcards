package com.doublea.androidflashcards.ui

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doublea.androidflashcards.R
import com.doublea.androidflashcards.extensions.inflate
import com.doublea.androidflashcards.extensions.launchFragment
import com.doublea.androidflashcards.viewmodel.FlashcardViewModel
import kotlinx.android.synthetic.main.fragment_flashcard_list.*

class FlashcardListFragment : LifecycleFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_flashcard_list)
    }

    lateinit var adapter: FlashcardAdapter
    lateinit var model: FlashcardViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model = FlashcardViewModel.create(activity)

        flashcard_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
        }

        initAdapter()

        model.getModelData().observe(this, Observer { if (it != null) adapter.dataSource = it })
    }

    fun initAdapter() {
        if (flashcard_list.adapter == null) {
            adapter = FlashcardAdapter {
                model.select(it)
                FlashcardQuizFragment().launchFragment(fragmentManager)

            }
        }
        flashcard_list.adapter = adapter
    }
}

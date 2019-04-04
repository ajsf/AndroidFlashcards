package com.doublea.androidflashcards.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doublea.androidflashcards.R
import com.doublea.androidflashcards.extensions.inflate
import com.doublea.androidflashcards.extensions.launchFragment
import kotlinx.android.synthetic.main.fragment_flashcard_list.*

class FlashcardListFragment : FlashcardBaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.fragment_flashcard_list)
    }

    private lateinit var adapter: FlashcardAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        flashcard_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        initAdapter()
        viewModel.flashcards
            .observe(this, Observer { if (it != null) adapter.setFlashcards(it) })
    }

    private fun initAdapter() {
        if (flashcard_list.adapter == null) {
            adapter = FlashcardAdapter { view, index ->
                viewModel.select(index)
                FlashcardQuizFragment().launchFragment(requireFragmentManager(), sharedView = view)
            }
        }
        flashcard_list.adapter = adapter
    }
}

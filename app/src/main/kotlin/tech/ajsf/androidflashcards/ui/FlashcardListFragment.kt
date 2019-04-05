package tech.ajsf.androidflashcards.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSmoothScroller
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.ajsf.androidflashcards.R
import tech.ajsf.androidflashcards.extensions.inflate
import tech.ajsf.androidflashcards.extensions.launchFragment
import tech.ajsf.androidflashcards.model.Category
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
    private lateinit var smoothScroller: LinearSmoothScroller

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        smoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int {
                return LinearSmoothScroller.SNAP_TO_START
            }
        }

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
            adapter = FlashcardAdapter { view, id ->
                viewModel.select(id)
                FlashcardQuizFragment().launchFragment(requireFragmentManager(), sharedView = view)
            }
        }
        flashcard_list.adapter = adapter
    }

    fun scrollToCategory(category: Category) {
        val pos = adapter.findPositionForCategory(category.description)
        smoothScroller.targetPosition = pos
        flashcard_list.layoutManager?.startSmoothScroll(smoothScroller)
    }
}

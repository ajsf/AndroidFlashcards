package com.doublea.androidflashcards.ui

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.doublea.androidflashcards.R
import com.doublea.androidflashcards.extensions.inflate
import com.doublea.androidflashcards.extensions.launchFragment
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.viewmodel.FlashcardViewModel
import kotlinx.android.synthetic.main.fragment_flashcard_quiz.*
import kotlinx.android.synthetic.main.flashcard_answer.*

import java.net.URL

class FlashcardQuizFragment : LifecycleFragment() {

    var urls = emptyArray<URL>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_flashcard_quiz)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel(FlashcardViewModel.create(activity))
        action_edit.setOnClickListener { EditFlashcardFragment().launchFragment(fragmentManager) }
    }

    private fun bindViewModel(model: FlashcardViewModel) {
        model.selectedFlashcard.observe(this, Observer { if (it != null) initFlashcardView(it) })
        model.showAnswer.observe(this, Observer { if (it == true) showAnswer() else hideAnswer() })
        action_view_answer.setOnClickListener { model.showAnswer.value = true }
    }

    private fun showAnswer() {
        flashcard_answer.visibility = View.VISIBLE
        action_view_answer.visibility = View.GONE
    }

    private fun hideAnswer() {
        flashcard_answer.visibility = View.INVISIBLE
        action_view_answer.visibility = View.VISIBLE
    }

    private fun initFlashcardView(flashcard: Flashcard) {
        with(flashcard) {
            question_tv.text = question
            answer_tv.text = answer
            initUrlList(this)
            flashcard_answer.visibility = View.INVISIBLE
        }
    }

    private fun initUrlList(flashcard: Flashcard) {
        urls = flashcard.links.toTypedArray()
        val urlAdapter = ArrayAdapter<URL>(activity.baseContext, R.layout.url_list_item, urls)
        with(url_list) {
            adapter = urlAdapter
            divider = null
            dividerHeight = 0
        }
    }
}

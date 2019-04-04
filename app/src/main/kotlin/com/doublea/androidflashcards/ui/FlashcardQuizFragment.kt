package com.doublea.androidflashcards.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.doublea.androidflashcards.R
import com.doublea.androidflashcards.extensions.inflate
import com.doublea.androidflashcards.extensions.launchFragment
import com.doublea.androidflashcards.model.Flashcard
import kotlinx.android.synthetic.main.flashcard_answer.*
import kotlinx.android.synthetic.main.fragment_flashcard_quiz.*
import java.net.URL

class FlashcardQuizFragment : FlashcardBaseFragment() {

    private var urls = emptyArray<URL>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.fragment_flashcard_quiz)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
        action_edit.setOnClickListener {
            EditFlashcardFragment().launchFragment(
                requireFragmentManager()
            )
        }
    }

    private fun bindViewModel() {
        viewModel.quizViewStateLiveData.observe(this, Observer {
            it?.let { viewState ->
                initFlashcardView(viewState.selectedFlashcard)
                if (viewState.showAnswer) showAnswer() else hideAnswer()
            }
        })
        action_view_answer.setOnClickListener { viewModel.showAnswer() }
        action_back.setOnClickListener { fragmentManager?.popBackStack() }
    }

    private fun initFlashcardView(flashcard: Flashcard) {
        val inflater = LayoutInflater.from(context)

        with(flashcard) {
            quiz_question_tv.text = question
            answer_tv.text = answer
            flashcard_answer.visibility = View.INVISIBLE
            url_list.removeAllViews()

            urls = flashcard.links.toTypedArray()
            urls.forEach {
                val tv = inflater.inflate(R.layout.url_list_item, null) as TextView
                tv.text = it.toString()
                url_list.addView(tv)
            }
        }
    }

    private fun hideAnswer() {
        flashcard_answer.visibility = View.INVISIBLE
        action_view_answer.visibility = View.VISIBLE
    }

    private fun showAnswer() {
        flashcard_answer.visibility = View.VISIBLE
        action_view_answer.visibility = View.GONE
    }
}

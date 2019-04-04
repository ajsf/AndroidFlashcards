package com.doublea.androidflashcards.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doublea.androidflashcards.R
import com.doublea.androidflashcards.extensions.inflate
import com.doublea.androidflashcards.model.Flashcard
import kotlinx.android.synthetic.main.fragment_edit_flashcard.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.toast

class EditFlashcardFragment : FlashcardBaseFragment() {

    lateinit var flashcard: Flashcard

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_edit_flashcard)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindViewModel()
        initClickListeners()
    }

    private fun bindViewModel() {
        viewModel.quizViewStateLiveData.observe(this, Observer {
            if (it?.selectedFlashcard != null) {
                flashcard = it.selectedFlashcard
                updateView()
            }
        })
    }

    private fun updateView() {
        with(flashcard) {
            edit_question_tv.text = question
            if (viewModel.editedText == null) {
                answer_edit_text.setText(answer)
            } else {
                answer_edit_text.setText(viewModel.editedText)
            }
        }
    }

    private fun initClickListeners() {
        action_clear.setOnClickListener { answer_edit_text.setText("") }
        action_reset.setOnClickListener { answer_edit_text.setText(flashcard.answer) }
        action_save.setOnClickListener { updateFlashcard(answer_edit_text.text.toString()) }
    }

    private fun updateFlashcard(newAnswer: String) {
        doAsync { viewModel.updateSelectedFlashcard(newAnswer) }
        toast("Flashcard Updated")
    }

    override fun onDestroyView() {
        viewModel.editedText = answer_edit_text.text.toString()
        super.onDestroyView()
    }
}
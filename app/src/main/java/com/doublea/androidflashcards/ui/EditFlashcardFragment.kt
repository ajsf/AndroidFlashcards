package com.doublea.androidflashcards.ui


import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.doublea.androidflashcards.R
import com.doublea.androidflashcards.extensions.inflate
import com.doublea.androidflashcards.model.Flashcard
import com.doublea.androidflashcards.viewmodel.FlashcardViewModel
import kotlinx.android.synthetic.main.fragment_edit_flashcard.*

class EditFlashcardFragment : LifecycleFragment() {

    lateinit var flashcard: Flashcard
    lateinit var model: FlashcardViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_edit_flashcard)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = FlashcardViewModel.create(activity)
        bindViewModel()
        initClickListeners()
    }

    private fun bindViewModel() {
        model.selectedFlashcard.observe(this, Observer {
            if (it != null) {
                flashcard = it
                initView()
            }
        })
    }

    private fun initView() {
        with(flashcard) {
            question_tv.text = question
            if (model.editedText == null) {
                answer_edit_text.setText(answer)
            } else {
                answer_edit_text.setText(model.editedText)
            }
        }
    }

    private fun initClickListeners() {
        action_clear.setOnClickListener { answer_edit_text.setText("") }
        action_reset.setOnClickListener { answer_edit_text.setText(flashcard.answer) }

    }

    override fun onDestroyView() {
        model.editedText = answer_edit_text.text.toString()
        super.onDestroy()
    }
}
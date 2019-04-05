package tech.ajsf.androidflashcards.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.Fragment
import tech.ajsf.androidflashcards.viewmodel.FlashcardViewModel

abstract class FlashcardBaseFragment : Fragment() {

    lateinit var viewModel: FlashcardViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(FlashcardViewModel::class.java)
        }
    }
}
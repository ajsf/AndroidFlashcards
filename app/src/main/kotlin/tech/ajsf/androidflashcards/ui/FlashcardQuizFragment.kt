package tech.ajsf.androidflashcards.ui

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.flashcard_answer.*
import kotlinx.android.synthetic.main.flashcard_back.*
import kotlinx.android.synthetic.main.flashcard_front.*
import kotlinx.android.synthetic.main.fragment_flashcard_quiz.*
import tech.ajsf.androidflashcards.R
import tech.ajsf.androidflashcards.extensions.inflate
import tech.ajsf.androidflashcards.extensions.launchFragment
import tech.ajsf.androidflashcards.model.Flashcard
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
                hideAnswer()
                if (viewState.showAnswer) showAnswer() else hideAnswer()
            }
        })
        action_view_answer.setOnClickListener { viewModel.showAnswer() }
        back_action_back.setOnClickListener { exitActivity() }
        back_action_front.setOnClickListener { exitActivity() }
    }

    private fun exitActivity() {
        ObjectAnimator.ofFloat(flashcard_layout, "alpha", 0f).apply {
            duration = 500
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    fragmentManager?.popBackStack()
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
            start()
        }
    }

    private fun initFlashcardView(flashcard: Flashcard) {
        val inflater = LayoutInflater.from(context)

        with(flashcard) {
            quiz_question_tv_back.text = question
            quiz_question_tv_front.text = question
            answer_tv.text = answer
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
        flashcard_back.visibility = View.GONE
    }

    private fun showAnswer() {
        flashcard_back.visibility = View.VISIBLE
        flashcard_front.cameraDistance = resources.displayMetrics.density * 8000
        flashcard_back.cameraDistance = resources.displayMetrics.density * 8000

        val outAnim = AnimatorInflater.loadAnimator(context, R.animator.flip_out_animation)
        outAnim.setTarget(flashcard_front)

        val inAnim = AnimatorInflater.loadAnimator(context, R.animator.flip_in_animation)
        inAnim.setTarget(flashcard_back)
        outAnim.start()
        inAnim.start()
    }
}

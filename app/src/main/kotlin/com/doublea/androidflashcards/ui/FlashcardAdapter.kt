package com.doublea.androidflashcards.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.doublea.androidflashcards.R
import com.doublea.androidflashcards.extensions.inflate
import com.doublea.androidflashcards.model.Flashcard
import kotlinx.android.synthetic.main.list_item.view.*

class FlashcardAdapter(val clickListener: (Flashcard) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataSource: List<Flashcard> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FlashcardViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as FlashcardViewHolder
        holder.bind(dataSource[position])
    }

    override fun getItemCount() = dataSource.size

    inner class FlashcardViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.list_item)) {
        fun bind(flashcard: Flashcard) = with(itemView) {
            tv_question.text = flashcard.question
            setOnClickListener { clickListener(flashcard) }
        }
    }

}
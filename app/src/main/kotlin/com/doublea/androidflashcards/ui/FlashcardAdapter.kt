package com.doublea.androidflashcards.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.doublea.androidflashcards.R
import com.doublea.androidflashcards.extensions.inflate
import com.doublea.androidflashcards.model.Categories
import com.doublea.androidflashcards.model.Flashcard
import kotlinx.android.synthetic.main.list_item.view.*

sealed class AdapterItem
data class Header(val category: String) : AdapterItem()
data class Item(val question: String, val index: Int) : AdapterItem()

class FlashcardAdapter(val clickListener: (View, Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataSource: List<AdapterItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setFlashcards(flashcards: List<Flashcard>) {

        dataSource = flashcards
            .groupBy { it.category }
            .map {
                println("--------------- $it -------------")
                Categories.valueOf(it.key) to it.value.mapIndexed { index, flashcard ->
                    Item(flashcard.question, index)
                }
            }
            .flatMap { listOf(Header(it.first.description)) + it.second }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataSource[position]) {
            is Header -> 0
            is Item -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        println("onBindViewHolder")
        return when (viewType) {
            0 -> HeaderViewHolder(parent)
            else -> ItemViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        println("onBindViewHolder. position: $position")
        when (holder) {
            is HeaderViewHolder -> {
                (holder.itemView as TextView).text = (dataSource[position] as Header).category
            }
            is ItemViewHolder -> {
                holder.bind(dataSource[position] as Item)
            }
        }
    }

    override fun getItemCount() = dataSource.size

    inner class ItemViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.list_item)) {

        fun bind(item: Item) = with(itemView) {
            tv_question.text = item.question
            setOnClickListener { clickListener(this, item.index) }
        }
    }

    inner class HeaderViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.list_header))
}
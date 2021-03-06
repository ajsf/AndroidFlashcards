package tech.ajsf.androidflashcards.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*
import tech.ajsf.androidflashcards.R
import tech.ajsf.androidflashcards.extensions.inflate
import tech.ajsf.androidflashcards.model.Category
import tech.ajsf.androidflashcards.model.Flashcard

sealed class AdapterItem
data class Header(val category: String) : AdapterItem()
data class Item(val question: String, val id: String) : AdapterItem()

class FlashcardAdapter(val clickListener: (View, String) -> Unit) :
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
                Category.valueOf(it.key) to it.value.map { flashcard ->
                    Item(flashcard.question, flashcard.id)
                }
            }.sortedBy {
                it.first.ordinal
            }
            .flatMap { listOf(Header(it.first.description)) + it.second }
    }

    fun findPositionForCategory(category: String): Int = dataSource.indexOf(Header(category))

    override fun getItemViewType(position: Int): Int {
        return when (dataSource[position]) {
            is Header -> 0
            is Item -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> HeaderViewHolder(parent)
            else -> ItemViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
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
            setOnClickListener { clickListener(this, item.id) }
        }
    }

    inner class HeaderViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.list_header))
}
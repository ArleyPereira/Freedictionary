package com.example.freedictionary.presenter.words

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.freedictionary.databinding.WordItemBinding
import com.example.freedictionary.domain.model.WordDomain

class WordsAdapter(private val itemSelected: (WordDomain) -> Unit) :
    PagingDataAdapter<WordDomain, WordsAdapter.WordViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<WordDomain>() {
            override fun areItemsTheSame(
                oldItem: WordDomain,
                newItem: WordDomain
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: WordDomain,
                newItem: WordDomain
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            WordItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = getItem(position)

        holder.binding.textWord.text = word?.word

        holder.itemView.setOnClickListener {
            if (word != null) {
                itemSelected(word)
            }
        }
    }

    inner class WordViewHolder(val binding: WordItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}
package com.example.freedictionary.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freedictionary.databinding.WordItemBinding
import com.example.freedictionary.domain.model.WordRefFirebase

class FavoritesAndHistoricAdapter(
    private val words: List<WordRefFirebase>,
    private val itemSelected: (WordRefFirebase) -> Unit
) : RecyclerView.Adapter<FavoritesAndHistoricAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            WordItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val wordRefFirebase = words[position]

        holder.binding.textWord.text = wordRefFirebase.word

        holder.itemView.setOnClickListener { itemSelected(wordRefFirebase) }
    }

    override fun getItemCount() = words.size

    inner class MyViewHolder(val binding: WordItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}
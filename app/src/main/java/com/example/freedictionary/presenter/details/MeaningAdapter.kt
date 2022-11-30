package com.example.freedictionary.presenter.details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freedictionary.R
import com.example.freedictionary.data.remote.model.Definition
import com.example.freedictionary.databinding.ItemMeaningBinding

class MeaningAdapter(
    private val context: Context,
    private val definitionList: List<Definition>
) : RecyclerView.Adapter<MeaningAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemMeaningBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val definition = definitionList[position]

        holder.binding.textMeaning.text =
            context.getString(
                R.string.text_item_meaning_details_fragment,
                (position + 1),
                definition.definition
            )
    }

    override fun getItemCount() = definitionList.size

    inner class MyViewHolder(val binding: ItemMeaningBinding) :
        RecyclerView.ViewHolder(binding.root)

}
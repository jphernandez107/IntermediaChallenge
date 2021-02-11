package com.jphernandez.intermediachallenge.characterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jphernandez.intermediachallenge.R
import com.jphernandez.intermediachallenge.data.Character
import com.jphernandez.intermediachallenge.helpers.displayThumbnail

class CharactersAdapter(private val onCharacterClick: (Character) -> Unit): PagingDataAdapter<Character, CharactersAdapter.ViewHolder>(CharacterDiffCallback) {

    class ViewHolder(view: View, val onCharacterClick: (Character) -> Unit): RecyclerView.ViewHolder(view) {
        val characterImageView: ImageView = view.findViewById(R.id.character_image)
        val characterNameTextView: TextView = view.findViewById(R.id.character_name)
        val characterDescriptionTextView: TextView = view.findViewById(R.id.character_description)
        var character: Character? = null

        init {
            itemView.setOnClickListener {
                character?.let{
                    onCharacterClick(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false)
        return ViewHolder(
            view,
            onCharacterClick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.character = getItem(position)
        holder.characterNameTextView.text = getItem(position)?.name ?: ""
        holder.characterDescriptionTextView.text = getItem(position)?.description ?: ""
//        val thumbnailUrl = getItem(position).thumbnail.path + "." + getItem(position).thumbnail.extension
        displayThumbnail(getItem(position)?.thumbnail?.path, getItem(position)?.thumbnail?.extension, holder.characterImageView)
    }
}




object CharacterDiffCallback: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem
}
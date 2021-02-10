package com.jphernandez.intermediachallenge.characterDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jphernandez.intermediachallenge.R
import com.jphernandez.intermediachallenge.data.Comic

class CharacterComicsAdapter: ListAdapter<Comic, CharacterComicsAdapter.ViewHolder>(ComicDiffCallback) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val comicName: TextView = view.findViewById(R.id.comic_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_comic_detail_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.comicName.text = getItem(position).name
    }

}


object ComicDiffCallback: DiffUtil.ItemCallback<Comic>() {
    override fun areItemsTheSame(oldItem: Comic, newItem: Comic) = oldItem.resourceURI == newItem.resourceURI
    override fun areContentsTheSame(oldItem: Comic, newItem: Comic) = oldItem == newItem
}
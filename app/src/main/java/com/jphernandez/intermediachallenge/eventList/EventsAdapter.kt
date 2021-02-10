package com.jphernandez.intermediachallenge.eventList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jphernandez.intermediachallenge.R
import com.jphernandez.intermediachallenge.characterDetails.CharacterComicsAdapter
import com.jphernandez.intermediachallenge.data.Event
import com.jphernandez.intermediachallenge.helpers.dateConverter
import com.jphernandez.intermediachallenge.helpers.displayThumbnail

class EventsAdapter: ListAdapter<Event, EventsAdapter.ViewHolder>(EventDiffCallback) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val eventImageView: ImageView = view.findViewById(R.id.event_image)
        val eventNameTextView: TextView = view.findViewById(R.id.event_name)
        val eventDateTextView1: TextView = view.findViewById(R.id.event_date_1)
        val eventDateTextView2: TextView = view.findViewById(R.id.event_date_2)
        val eventShowMoreButton: ImageView = view.findViewById(R.id.show_more_button)

        val eventComicsSection: ConstraintLayout = view.findViewById(R.id.event_comics_section)
        private val comicsRecyclerView: RecyclerView = view.findViewById(R.id.comics_recycler_view)
        val comicsAdapter: CharacterComicsAdapter = CharacterComicsAdapter()

        var event: Event? = null

        init {
            val layoutManager = GridLayoutManager(comicsRecyclerView.context, 1)
            comicsRecyclerView.layoutManager = layoutManager
            comicsRecyclerView.adapter = comicsAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.event = getItem(position)
        holder.eventNameTextView.text = getItem(position)?.title ?: ""
        holder.eventDateTextView1.text = dateConverter(getItem(position)?.start)
        holder.eventDateTextView2.text = dateConverter(getItem(position)?.end)

        holder.eventShowMoreButton.visibility = View.VISIBLE
        holder.eventShowMoreButton.setImageDrawable(
            ResourcesCompat.getDrawable(
                holder.eventImageView.resources,
                R.drawable.ic_arrow_down,
                null
            )
        )
        displayThumbnail(
            getItem(position)?.thumbnail?.path,
            getItem(position)?.thumbnail?.extension,
            holder.eventImageView
        )

        holder.eventShowMoreButton.setOnClickListener {
            holder.event?.let {
                it.isExtendedEvent = !it.isExtendedEvent
                notifyItemChanged(position)
            }
        }

        var icon = R.drawable.ic_arrow_down
        holder.eventComicsSection.visibility = View.GONE
        if (holder.event?.isExtendedEvent!!) {
            holder.eventComicsSection.visibility = View.VISIBLE
            icon = R.drawable.ic_arrow_up
        }
        holder.eventShowMoreButton.setImageDrawable(
            ResourcesCompat.getDrawable(
                holder.eventImageView.resources,
                icon,
                null
            )
        )
        holder.comicsAdapter.submitList(getItem(position).comics)
        if(holder.event?.comics?.isNullOrEmpty() == true) {
            holder.eventShowMoreButton.visibility = View.GONE
            holder.eventComicsSection.visibility = View.GONE
        }
    }
}




object EventDiffCallback: DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
}
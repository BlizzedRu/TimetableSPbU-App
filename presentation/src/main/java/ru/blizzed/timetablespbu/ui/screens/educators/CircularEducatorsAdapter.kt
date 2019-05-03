package ru.blizzed.timetablespbu.ui.screens.educators

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.ui.common.adapters.BaseSingleTypeListAdapter
import ru.blizzed.timetablespbu.ui.common.adapters.SimpleDiffCallback

class CircularEducatorsAdapter : BaseSingleTypeListAdapter<Educator, CircularEducatorViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : SimpleDiffCallback<Educator>() {
            override fun areItemsTheSame(oldItem: Educator, newItem: Educator): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: Educator, newItem: Educator): Boolean = oldItem.isFavorite == newItem.isFavorite
        }
    }

    var onEducatorClickListener: ((Educator) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircularEducatorViewHolder =
        CircularEducatorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_educator_circular, parent, false))

    override fun onBindViewHolder(holder: CircularEducatorViewHolder, position: Int, payloads: List<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder.bind(
            getItems()[position],
            onEducatorClickListener
        )
    }

}

package ru.blizzed.timetablespbu.ui.screens.search.educators

import android.view.ViewGroup
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.extensions.inflate
import ru.blizzed.timetablespbu.ui.common.adapters.BaseSingleTypeListAdapter
import ru.blizzed.timetablespbu.ui.common.adapters.SimpleDiffCallback

class EducatorsAdapter : BaseSingleTypeListAdapter<Educator, EducatorViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : SimpleDiffCallback<Educator>() {
            override fun areItemsTheSame(oldItem: Educator, newItem: Educator): Boolean =
                    oldItem == newItem

            override fun areContentsTheSame(oldItem: Educator, newItem: Educator): Boolean =
                    oldItem.fullName == newItem.fullName
        }
    }

    var onEducatorClickListener: ((Educator) -> Unit)? = null
    var onFavoriteClickListener: ((Educator) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducatorViewHolder =
            EducatorViewHolder(parent.inflate(R.layout.item_list_educator, parent))

    override fun onBindViewHolder(holder: EducatorViewHolder, position: Int, payloads: List<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder.bind(
                getItems()[position],
                onEducatorClickListener,
                onFavoriteClickListener
        )
    }

}

package ru.blizzed.timetablespbu.ui.screens.search.educators

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_search_educator.view.educatorEmployment
import kotlinx.android.synthetic.main.item_search_educator.view.educatorName
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.EducatorEntity
import ru.blizzed.timetablespbu.ui.common.adapters.BaseSingleTypeListAdapter
import ru.blizzed.timetablespbu.ui.common.adapters.SimpleDiffCallback
import ru.blizzed.timetablespbu.ui.screens.search.educators.EducatorsSearchAdapter.EducatorViewHolder

class EducatorsSearchAdapter : BaseSingleTypeListAdapter<EducatorEntity, EducatorViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : SimpleDiffCallback<EducatorEntity>() {
            override fun areItemsTheSame(oldItem: EducatorEntity, newItem: EducatorEntity): Boolean =
                    oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducatorViewHolder =
            EducatorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search_educator, parent, false))

    override fun onBindViewHolder(holder: EducatorViewHolder, position: Int, payloads: List<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder.bind(getItems()[position])
    }

    class EducatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(educator: EducatorEntity) {
            itemView.educatorName.text = educator.fullName
            itemView.educatorEmployment.text = educator.employments.firstOrNull()?.run {
                "$post, $department"
            }
        }

    }

}
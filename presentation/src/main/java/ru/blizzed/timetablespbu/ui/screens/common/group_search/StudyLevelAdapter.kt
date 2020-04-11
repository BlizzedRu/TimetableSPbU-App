package ru.blizzed.timetablespbu.ui.screens.common.group_search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.ui.common.adapters.BaseSingleTypeListAdapter
import ru.blizzed.timetablespbu.ui.common.adapters.SimpleDiffCallback

class StudyLevelAdapter : BaseSingleTypeListAdapter<StudyLevel, StudyLevelAdapter.StudyLevelViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : SimpleDiffCallback<StudyLevel>() {
            override fun areItemsTheSame(oldItem: StudyLevel, newItem: StudyLevel): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyLevelViewHolder {
        return StudyLevelViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_simple_text, parent, false))
    }

    override fun onBindViewHolder(holder: StudyLevelViewHolder, position: Int, payloads: List<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder.bind(getItems()[holder.adapterPosition])
    }

    class StudyLevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(level: StudyLevel) {
            (itemView as TextView).text = level.name
        }
    }

}

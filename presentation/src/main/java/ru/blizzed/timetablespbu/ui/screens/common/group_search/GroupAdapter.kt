package ru.blizzed.timetablespbu.ui.screens.common.group_search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.ui.common.adapters.BaseSingleTypeListAdapter
import ru.blizzed.timetablespbu.ui.common.adapters.SimpleDiffCallback

class GroupAdapter : BaseSingleTypeListAdapter<Group, GroupAdapter.GroupViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : SimpleDiffCallback<Group>() {
            override fun areItemsTheSame(oldItem: Group, newItem: Group): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        return GroupViewHolder(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false))
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int, payloads: List<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder.bind(getItems()[holder.adapterPosition])
    }

    class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(level: Group) {
            (itemView as TextView).text = level.alias
        }
    }

}

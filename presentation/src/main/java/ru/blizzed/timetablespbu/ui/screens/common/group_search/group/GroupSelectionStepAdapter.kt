package ru.blizzed.timetablespbu.ui.screens.common.group_search.group

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.extensions.inflateAsParent
import ru.blizzed.timetablespbu.ui.common.adapters.BaseSingleTypeListAdapter
import ru.blizzed.timetablespbu.ui.common.adapters.SimpleDiffCallback
import ru.blizzed.timetablespbu.ui.screens.common.group_search.group.GroupSelectionStepAdapter.ViewHolder

class GroupSelectionStepAdapter : BaseSingleTypeListAdapter<Group, ViewHolder>(DIFF_CALLBACK) {

  companion object {
    val DIFF_CALLBACK = object : SimpleDiffCallback<Group>() {
      override fun areItemsTheSame(oldItem: Group, newItem: Group): Boolean {
        return oldItem.id == newItem.id
      }
    }
  }

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)
    recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(parent.inflateAsParent(R.layout.item_simple_text))

  override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>) {
    super.onBindViewHolder(holder, position, payloads)
    holder.bind(getItems()[holder.adapterPosition])
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(group: Group) {
      (itemView as TextView).text = group.alias
    }
  }

}

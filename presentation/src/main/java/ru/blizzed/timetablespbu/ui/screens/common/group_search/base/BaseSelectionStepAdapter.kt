package ru.blizzed.timetablespbu.ui.screens.common.group_search.base

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.extensions.inflateAsParent
import ru.blizzed.timetablespbu.ui.common.adapters.BaseSingleTypeListAdapter
import ru.blizzed.timetablespbu.ui.common.adapters.SimpleDiffCallback2

typealias ItemTitleProvider<T> = T.() -> String

class BaseSelectionStepAdapter<Item>(
  private val titleProvider: ItemTitleProvider<Item>
) : BaseSingleTypeListAdapter<Item, BaseSelectionStepAdapter.ViewHolder>(createDiffCallback(titleProvider)) {

  companion object {
    fun <T> createDiffCallback(converter: ItemTitleProvider<T>) = object : SimpleDiffCallback2<T>() {
      override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        converter.invoke(oldItem) == converter.invoke(newItem)
    }
  }

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)
    recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(parent.inflateAsParent<View>(R.layout.item_simple_text))

  override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>) {
    super.onBindViewHolder(holder, position, payloads)
    titleProvider.invoke(getItems()[holder.adapterPosition]).let(holder::bind)
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(text: String) {
      (itemView as TextView).text = text
    }

  }

}

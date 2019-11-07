package ru.blizzed.timetablespbu.ui.common.adapters

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.blizzed.timetablespbu.extensions.setOnRippleClickListener

abstract class BaseSingleTypeListAdapter<Item, ItemViewHolder : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<Item>
) : RecyclerView.Adapter<ItemViewHolder>() {

    var onItemClickListener: ((Item, RecyclerView.ViewHolder) -> Unit)? = null

    private var differ = AsyncListDiffer(this, diffCallback)

    fun submitItems(list: List<Item>) = differ.submitList(list)

    fun getItems(): List<Item> = differ.currentList

    final override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        onBindViewHolder(holder, position, emptyList())

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int, payloads: List<Any>) {
        if (onItemClickListener != null) {
            holder.itemView.setOnRippleClickListener { onItemClickListener?.invoke(getItems()[holder.adapterPosition], holder) }
        } else {
            holder.itemView.setOnClickListener(null)
        }
    }

    override fun getItemCount() = getItems().size
}

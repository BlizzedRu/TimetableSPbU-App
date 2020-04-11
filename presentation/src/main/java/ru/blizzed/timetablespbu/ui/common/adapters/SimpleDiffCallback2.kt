package ru.blizzed.timetablespbu.ui.common.adapters

import androidx.recyclerview.widget.DiffUtil

abstract class SimpleDiffCallback2<T>: DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = areContentsTheSame(oldItem, newItem)

}

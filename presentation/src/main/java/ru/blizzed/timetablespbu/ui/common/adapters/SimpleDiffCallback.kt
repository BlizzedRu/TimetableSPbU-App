package ru.blizzed.timetablespbu.ui.common.adapters

import androidx.recyclerview.widget.DiffUtil

abstract class SimpleDiffCallback<T>: DiffUtil.ItemCallback<T>() {

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = areItemsTheSame(oldItem, newItem)

}
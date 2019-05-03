package ru.blizzed.timetablespbu.ui.screens.educators

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.extensions.inflate
import ru.blizzed.timetablespbu.ui.common.adapters.SimpleDiffCallback

class EducatorsMergedAdapter(
        private val favoritesAdapter: CircularEducatorsAdapter
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val HEADER_POSITION = 0
        private const val HEADERS_COUNT = 1

        private val DIFF_CALLBACK = object : SimpleDiffCallback<Educator>() {
            override fun areItemsTheSame(oldItem: Educator, newItem: Educator): Boolean =
                    oldItem == newItem

            override fun areContentsTheSame(oldItem: Educator, newItem: Educator): Boolean =
                    oldItem.isFavorite == newItem.isFavorite
        }
    }

    var onViewedClickListener: ((Educator) -> Unit)? = null
    var onFavoriteClickListener: ((Educator) -> Unit)? = null

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewTypes.HEADER.ordinal) {
            HeaderViewHolder(parent.inflate(R.layout.item_list_educators_header, parent))
        } else {
            EducatorViewHolder(parent.inflate(R.layout.item_list_educator, parent))
        }
    }

    override fun getItemCount(): Int = HEADERS_COUNT + differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = onBindViewHolder(holder, position, emptyList())

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        if (position == HEADER_POSITION) {
            (holder as HeaderViewHolder).apply {
                bindAdapter(favoritesAdapter)
                bindStubs(
                        shouldShowFavoriteEmptyStub = favoritesAdapter.itemCount == 0,
                        shouldShowViewedEmptyStub = differ.currentList.isEmpty()
                )
            }
        } else {
            (holder as EducatorViewHolder).bind(
                    differ.currentList[position - HEADERS_COUNT],
                    onViewedClickListener,
                    onFavoriteClickListener
            )
        }
    }

    override fun getItemViewType(position: Int): Int = ViewTypes.HEADER.ordinal.takeIf { position == HEADER_POSITION } ?: ViewTypes.ITEM.ordinal

    fun submitLists(favoriteList: List<Educator>, viewedList: List<Educator>) {
        val shouldUpdateHeader = favoriteList.isEmpty() && favoritesAdapter.itemCount != 0
                || favoriteList.isNotEmpty() && favoritesAdapter.itemCount == 0
                || viewedList.isEmpty() && differ.currentList.isNotEmpty()
                || viewedList.isNotEmpty() && differ.currentList.isEmpty()

        favoritesAdapter.submitItems(favoriteList)

        differ.submitList(viewedList)

        if (shouldUpdateHeader) notifyHeaderChanged()
    }

    private fun notifyHeaderChanged() {
        notifyItemChanged(HEADER_POSITION)
    }

    private class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val recycler: RecyclerView = itemView.findViewById(R.id.favoriteEducatorsRecycler)
        private val favoriteEmptyStub: View = itemView.findViewById(R.id.favoriteEducatorsEmptyStub)
        private val viewedEmptyStub: View = itemView.findViewById(R.id.viewedEducatorsEmptyStub)

        fun bindAdapter(favoriteAdapter: CircularEducatorsAdapter) {
            recycler.adapter = favoriteAdapter
        }

        fun bindStubs(
                shouldShowFavoriteEmptyStub: Boolean = false,
                shouldShowViewedEmptyStub: Boolean = false
        ) {
            recycler.isVisible = !shouldShowFavoriteEmptyStub
            favoriteEmptyStub.isVisible = shouldShowFavoriteEmptyStub
            viewedEmptyStub.isVisible = shouldShowViewedEmptyStub
        }
    }

    private enum class ViewTypes {
        HEADER, ITEM
    }

}

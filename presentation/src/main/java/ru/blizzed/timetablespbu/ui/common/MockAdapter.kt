package ru.blizzed.timetablespbu.ui.common

import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.extensions.context
import ru.blizzed.timetablespbu.extensions.inflateAsParent

class MockAdapter(
    @LayoutRes private val mockLayoutResId: Int,
    private val mockItemsCount: Int = 15
) : RecyclerView.Adapter<MockAdapter.MockViewHolder>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnItemTouchListener(DisablingItemTouchEventsListener)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        recyclerView.removeOnItemTouchListener(DisablingItemTouchEventsListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MockViewHolder =
        parent.inflateAsParent(mockLayoutResId).let(::MockViewHolder)

    override fun onBindViewHolder(holder: MockViewHolder, position: Int) = Unit

    override fun getItemCount(): Int = mockItemsCount

    class MockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            AnimationUtils.loadAnimation(context, R.anim.mock).let(itemView::startAnimation)
        }

    }

}

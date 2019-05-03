package ru.blizzed.timetablespbu.ui.common

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

object DisablingItemTouchEventsListener : RecyclerView.OnItemTouchListener {

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) = Unit

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent) = true

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) = Unit
}

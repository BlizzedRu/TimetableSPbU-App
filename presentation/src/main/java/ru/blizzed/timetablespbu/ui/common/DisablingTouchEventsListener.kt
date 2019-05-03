package ru.blizzed.timetablespbu.ui.common

import android.view.MotionEvent
import android.view.View

object DisablingTouchEventsListener : View.OnTouchListener {

    override fun onTouch(v: View?, event: MotionEvent?): Boolean = true

}

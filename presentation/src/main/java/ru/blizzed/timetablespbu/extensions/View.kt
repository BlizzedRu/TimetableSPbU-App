package ru.blizzed.timetablespbu.extensions

import android.view.View

fun View.setOnRippleClickListener(listener: () -> Unit) {
    setOnClickListener { view -> postDelayed({ if (hasWindowFocus()) listener() }, 150L) }
}
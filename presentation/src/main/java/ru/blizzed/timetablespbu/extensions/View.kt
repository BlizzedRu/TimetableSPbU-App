package ru.blizzed.timetablespbu.extensions

import android.view.View
import androidx.core.view.isVisible

fun View.setOnRippleClickListener(listener: () -> Unit) {
    setOnClickListener { postDelayed({ if (hasWindowFocus()) listener() }, 150L) }
}

inline var View.isVisibleAnimated: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        val visibilityChangeAction = { this.isVisible = value }
        animate().alpha(if (value) 1f else 0f)
                .setDuration(150L)
                .apply {
                    if (value) {
                        withStartAction(visibilityChangeAction)
                    } else {
                        withEndAction(visibilityChangeAction)
                    }
                }
                .start()
    }

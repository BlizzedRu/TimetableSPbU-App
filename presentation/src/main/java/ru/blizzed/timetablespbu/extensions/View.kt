package ru.blizzed.timetablespbu.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible

fun View.setOnRippleClickListener(listener: () -> Unit) {
    setOnClickListener { postDelayed({ if (hasWindowFocus()) listener.invoke() }, 150L) }
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

fun View.openKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).let {
        requestFocus()
        it.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

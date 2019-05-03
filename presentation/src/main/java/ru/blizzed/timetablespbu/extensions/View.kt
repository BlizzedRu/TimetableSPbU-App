package ru.blizzed.timetablespbu.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import ru.blizzed.timetablespbu.ui.common.DisablingTouchEventsListener

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

fun View.hideKeyboard() {
    clearFocus()
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)
}

fun View.inflate(@LayoutRes layoutRes: Int, parent: ViewGroup): View = LayoutInflater.from(context).inflate(layoutRes, parent, false)

/**
 * Be careful, it overrides all touch events
 */
fun View.disableTouchEvents() = setOnTouchListener(DisablingTouchEventsListener)

/**
 * Be careful, it overrides all touch events
 */
fun View.enableTouchEvents() = setOnTouchListener(null)

fun ViewGroup.inflateAsParent(@LayoutRes layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)

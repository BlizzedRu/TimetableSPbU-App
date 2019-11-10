package ru.blizzed.timetablespbu.ui.common

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import ru.blizzed.timetablespbu.extensions.getColorCompat

class FragmentStatusBarColorDelegate(
        private val activity: Activity,
        @ColorRes private val statusBarColor: Int
) : LifecycleObserver {

    private var initialStatusBarColor: Int = -1

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun setCustomStatusBarColor() {
        initialStatusBarColor = activity.window.statusBarColor
        activity.window.statusBarColor = activity.getColorCompat(statusBarColor)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun setInitialStatusBarColor() {
        activity.window.statusBarColor = initialStatusBarColor
    }

}

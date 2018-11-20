package ru.blizzed.timetablespbu.ui.core

import androidx.annotation.LayoutRes

@Target(AnnotationTarget.CLASS)
@Retention
annotation class ScreenParams(@LayoutRes val layoutRes: Int)
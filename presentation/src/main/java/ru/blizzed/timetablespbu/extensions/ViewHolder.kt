package ru.blizzed.timetablespbu.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

fun <T : View> RecyclerView.ViewHolder.findViewById(@IdRes resId: Int): T = itemView.findViewById(resId)

val RecyclerView.ViewHolder.context: Context
    get() = itemView.context

fun RecyclerView.ViewHolder.getText(@StringRes resId: Int): CharSequence = context.getText(resId)

fun RecyclerView.ViewHolder.getString(@StringRes resId: Int): String = context.getString(resId)

fun RecyclerView.ViewHolder.getString(@StringRes resId: Int, vararg args: Any): String = context.getString(resId, *args)

@ColorInt
fun RecyclerView.ViewHolder.getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(context, resId)

fun RecyclerView.ViewHolder.getColorStateList(@ColorRes resId: Int): ColorStateList? = ContextCompat.getColorStateList(context, resId)

fun RecyclerView.ViewHolder.getDrawable(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(context, resId)

package ru.blizzed.timetablespbu.data.utils

import android.graphics.Color
import androidx.annotation.ColorInt
import java.util.Random

class ColorGenerator(@ColorInt private val baseColor: Int) {

    @ColorInt
    fun randomPastel(): Int =
        Random(System.nanoTime()).run {
            val randomBound = Byte.MAX_VALUE * 2
            val red = (Color.red(baseColor) + nextInt(randomBound)) / 2
            val green = (Color.green(baseColor) + nextInt(randomBound)) / 2
            val blue = (Color.blue(baseColor) + nextInt(randomBound)) / 2

            Color.rgb(red, green, blue)
        }

}

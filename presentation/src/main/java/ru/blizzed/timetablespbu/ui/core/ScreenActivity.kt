package ru.blizzed.timetablespbu.ui.core

import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import ru.blizzed.timetablespbu.R
import kotlin.reflect.KClass

abstract class ScreenActivity : AppCompatActivity() {

    fun <State : Parcelable> openScreen(
        screenClass: KClass<out Screen<out State>>,
        state: Parcelable = NoState
    ) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.screen_container,
                ScreenFragment.newInstance(screenClass, state)
            )
            .commit()
    }

}
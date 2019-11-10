package ru.blizzed.timetablespbu.core

import androidx.annotation.IdRes
import androidx.navigation.NavController

interface NavigationProvider {

    @get:IdRes
    val navigationControllerId: Int

    val navigation: NavController

}

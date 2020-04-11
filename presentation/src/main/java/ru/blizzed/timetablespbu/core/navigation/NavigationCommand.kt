package ru.blizzed.timetablespbu.core.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(val direction: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
    data class BackTo(@IdRes val directionId: Int) : NavigationCommand()
}

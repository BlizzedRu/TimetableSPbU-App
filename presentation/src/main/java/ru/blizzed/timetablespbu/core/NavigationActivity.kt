package ru.blizzed.timetablespbu.core

import androidx.navigation.NavController
import androidx.navigation.Navigation

abstract class NavigationActivity : BaseActivity(), NavigationProvider {

    override val navigation: NavController by lazy {
        Navigation.findNavController(this, navigationControllerId)
    }

}

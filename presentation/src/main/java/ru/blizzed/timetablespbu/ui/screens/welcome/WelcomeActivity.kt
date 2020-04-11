package ru.blizzed.timetablespbu.ui.screens.welcome

import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.core.NavigationActivity

class WelcomeActivity : NavigationActivity() {

    override val layoutRes: Int = R.layout.activity_welcome

    override val navigationControllerId: Int = R.id.welcomeNavigationHostFragment

}

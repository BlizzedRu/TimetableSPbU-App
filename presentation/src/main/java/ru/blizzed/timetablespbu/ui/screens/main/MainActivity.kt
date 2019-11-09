package ru.blizzed.timetablespbu.ui.screens.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.core.NoState
import ru.blizzed.timetablespbu.ui.core.ScreenFragment
import ru.blizzed.timetablespbu.ui.screens.main.bookmarks.BookmarksScreen
import ru.blizzed.timetablespbu.ui.screens.main.schedule.ScheduleScreen
import ru.blizzed.timetablespbu.ui.screens.main.search.SearchScreen
import ru.blizzed.timetablespbu.ui.screens.main.search.SearchScreenState
import kotlin.reflect.KClass

class MainActivity : FragmentActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(
                    Intent(context, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }

    private lateinit var bottomNavigationController: BottomNavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeBottomNavigation()
    }

    private fun initializeBottomNavigation() {
        bottomNavigationController = BottomNavigationController(
                defaultItemId = R.id.main_navigation_schedule,
                onItemSelectedListener = ::onNavigationItemSelected
        ).apply { startUpWithNavigationView(navigationView) }
    }

    private fun onNavigationItemSelected(menuItem: MenuItem) {
        actionBar?.title = menuItem.title
    }

    private fun openScreen(screen: Screens) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.screen_container, ScreenFragment.instantiate(this, screen.screenClass, screen.state))
                .commit()
    }

    private inner class BottomNavigationController(
            private val defaultItemId: Int? = null,
            private val onItemSelectedListener: (MenuItem) -> Unit
    ) {

        private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            onItemSelected(item)
            true
        }

        private lateinit var navigationMenu: Menu

        fun startUpWithNavigationView(navigation: BottomNavigationView) {
            navigationMenu = navigation.menu
            navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            defaultItemId?.let {
                navigation.selectedItemId = it
                onItemSelected(navigationMenu.findItem(it))
            }
        }

        private fun onItemSelected(item: MenuItem) {
            Screens.values().first { screen ->
                screen.navigationId == item.itemId
            }.also(::openScreen)

            onItemSelectedListener.invoke(item)
        }

    }

    private enum class Screens(@IdRes val navigationId: Int, val screenClass: KClass<out ScreenFragment<*>>, val state: Parcelable) {
        SEARCH(R.id.main_navigation_search, SearchScreen::class, SearchScreenState()),
        SCHEDULE(R.id.main_navigation_schedule, ScheduleScreen::class, NoState),
        BOOKMARKS(R.id.main_navigation_bookmarks, BookmarksScreen::class, NoState)
    }

}

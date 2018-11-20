package ru.blizzed.timetablespbu.ui

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import android.view.Menu
import android.view.MenuItem
import androidx.core.util.contains
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.core.NoState
import ru.blizzed.timetablespbu.ui.core.Screen
import ru.blizzed.timetablespbu.ui.core.ScreenActivity
import ru.blizzed.timetablespbu.ui.screens.bookmarks.BookmarksScreen
import ru.blizzed.timetablespbu.ui.screens.schedule.ScheduleScreen
import ru.blizzed.timetablespbu.ui.screens.search.SearchScreen
import ru.blizzed.timetablespbu.ui.screens.search.SearchScreenState
import kotlin.reflect.KClass

class MainActivity : ScreenActivity() {

    companion object {
        private const val DEFAULT_NAVIGATION_ITEM_ID = R.id.main_navigation_schedule
    }

    private lateinit var bottomNavigationController: BottomNavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeBottomNavigation()
    }

    private fun initializeBottomNavigation() {
        val screens = SparseArray<Pair<KClass<out Screen<out Parcelable>>, Parcelable>>().apply {
            put(R.id.main_navigation_search, SearchScreen::class to SearchScreenState())
            put(R.id.main_navigation_schedule, ScheduleScreen::class to NoState)
            put(R.id.main_navigation_bookmarks, BookmarksScreen::class to NoState)
        }

        bottomNavigationController = BottomNavigationController(
            screens = screens,
            defaultItemId = DEFAULT_NAVIGATION_ITEM_ID,
            onItemSelectedListener = ::onNavigationItemSelected
        ).apply { startUpWithNavigationView(navigationView) }
    }

    private fun onNavigationItemSelected(menuItem: MenuItem) {
        supportActionBar?.title = menuItem.title
    }

    private inner class BottomNavigationController(
        private val screens: SparseArray<Pair<KClass<out Screen<out Parcelable>>, Parcelable>>,
        private val defaultItemId: Int? = null,
        private val onItemSelectedListener: (MenuItem) -> Unit
    ) {

        private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            screens.contains(item.itemId).also {
                if (it) onItemSelected(item)
            }
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
            screens[item.itemId].apply {
                onItemSelectedListener(item)
                openScreen(first, second)
            }
        }

    }

}

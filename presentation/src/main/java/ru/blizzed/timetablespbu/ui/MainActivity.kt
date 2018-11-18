package ru.blizzed.timetablespbu.ui

import android.os.Bundle
import android.util.SparseArray
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.contains
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.common.BaseFragment
import ru.blizzed.timetablespbu.ui.screens.actions.SearchFragment
import ru.blizzed.timetablespbu.ui.screens.bookmarks.BookmarksFragment
import ru.blizzed.timetablespbu.ui.screens.schedule.ScheduleFragment

class MainActivity : AppCompatActivity() {

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
        val pages = SparseArray<BaseFragment>().apply {
            put(R.id.main_navigation_search, SearchFragment())
            put(R.id.main_navigation_schedule, ScheduleFragment())
            put(R.id.main_navigation_bookmarks, BookmarksFragment())
        }

        bottomNavigationController = BottomNavigationController(
            containerId = R.id.main_fragment_container,
            fragmentManager = supportFragmentManager,
            fragments = pages,
            defaultItemId = DEFAULT_NAVIGATION_ITEM_ID,
            onItemSelectedListener = ::onNavigationItemSelected
        ).apply { startUpWithNavigationView(main_navigation_view) }
    }

    private fun onNavigationItemSelected(menuItem: MenuItem, fragment: BaseFragment) {
        supportActionBar?.title = menuItem.title
    }

    private class BottomNavigationController(
        @IdRes private val containerId: Int,
        private val fragmentManager: FragmentManager,
        private val fragments: SparseArray<BaseFragment>,
        private val defaultItemId: Int? = null,
        private val onItemSelectedListener: (MenuItem, BaseFragment) -> Unit
    ) {

        private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            fragments.contains(item.itemId).also {
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

        fun getFragment(itemId: Int): BaseFragment = fragments[itemId]

        private fun onItemSelected(item: MenuItem) {
            fragments[item.itemId].also {
                onItemSelectedListener(item, it)
                fragmentManager.beginTransaction()
                    .replace(containerId, it)
                    .commit()
            }
        }

    }

}

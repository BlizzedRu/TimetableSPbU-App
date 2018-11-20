package ru.blizzed.timetablespbu.ui.screens.search

import android.util.ArrayMap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.core.InjectViewModel
import ru.blizzed.timetablespbu.ui.core.Screen
import ru.blizzed.timetablespbu.ui.core.ScreenParams
import ru.blizzed.timetablespbu.viewmodel.SearchViewModel

@ScreenParams(R.layout.fragment_search)
class SearchScreen(screenContext: ScreenContext) : Screen<SearchScreenState>(screenContext) {

    private val searchPages = ArrayMap<Fragment, String>().apply {
        put(SearchPageFragment(), "Преподы")
        put(SearchPageFragment(), "Кабинеты")
    }

    @field:InjectViewModel(SearchViewModel::class)
    private lateinit var viewModel: SearchViewModel

    private val viewPager = findViewById<ViewPager>(R.id.viewPager)
    private val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

    init {
        viewPager.adapter = SearchPagerAdapter(activity.supportFragmentManager!!, searchPages)
        tabLayout.setupWithViewPager(viewPager)

        SearchController(activity, findViewById(R.id.searchContainer)).observe(viewModel::setSearchQuery)
    }

    private class SearchPagerAdapter(
        fragmentManager: FragmentManager,
        private val pages: ArrayMap<Fragment, String>
    ) : FragmentPagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment = pages.keyAt(position)

        override fun getCount() = pages.size

        override fun getPageTitle(position: Int): String = pages.valueAt(position)
    }

}
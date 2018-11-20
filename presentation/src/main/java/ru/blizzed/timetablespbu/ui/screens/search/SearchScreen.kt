package ru.blizzed.timetablespbu.ui.screens.search

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.core.InjectViewModel
import ru.blizzed.timetablespbu.ui.core.Screen
import ru.blizzed.timetablespbu.ui.core.ScreenParams
import ru.blizzed.timetablespbu.viewmodel.SearchViewModel

@ScreenParams(R.layout.screen_search)
class SearchScreen(screenContext: ScreenContext) : Screen<SearchScreenState>(screenContext) {

    @field:InjectViewModel(SearchViewModel::class)
    private lateinit var viewModel: SearchViewModel

    private val viewPager = findViewById<ViewPager>(R.id.viewPager)
    private val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

    init {
        viewPager.adapter = SearchTabsAdapter(fragment)
        tabLayout.setupWithViewPager(viewPager)

        SearchController(activity, findViewById(R.id.searchContainer)).observe(viewModel::setSearchQuery)
    }

}
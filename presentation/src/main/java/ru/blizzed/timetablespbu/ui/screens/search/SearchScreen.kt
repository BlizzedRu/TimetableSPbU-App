package ru.blizzed.timetablespbu.ui.screens.search

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.screen_search.tabLayout
import kotlinx.android.synthetic.main.screen_search.viewPager
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.core.ScreenFragment
import ru.blizzed.timetablespbu.ui.core.ScreenParams

@ScreenParams(R.layout.screen_search)
class SearchScreen : ScreenFragment<SearchScreenState>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = SearchTabsAdapter(this)
        tabLayout.setupWithViewPager(viewPager)
    }

}
package ru.blizzed.timetablespbu.ui.screens.main.search

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.screen_search.*
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.core.NoState
import ru.blizzed.timetablespbu.ui.core.ScreenFragment
import ru.blizzed.timetablespbu.ui.core.ScreenParams
import ru.blizzed.timetablespbu.ui.screens.main.search.educators.EducatorsSearchScreen

@ScreenParams(R.layout.screen_search)
class SearchScreen : ScreenFragment<SearchScreenState>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = SearchTabsAdapter(this)
        tabLayout.setupWithViewPager(viewPager)

        search.setOnClickListener {
            when (viewPager.currentItem) {
                SearchTabs.EDUCATORS.ordinal -> activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.screen_container, ScreenFragment.create(context!!, EducatorsSearchScreen::class, NoState))
                        ?.commit()
                SearchTabs.CLASSROOMS.ordinal -> {}/*activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.screen_container, BaseFacultiesSearchFragment() /*ScreenFragment.create(context!!, FacultiesSearchScreen::class, NoState)*/)
                        ?.commit()*/
            }
        }
    }

}

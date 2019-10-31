package ru.blizzed.timetablespbu.ui.screens.search

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.core.NoState
import ru.blizzed.timetablespbu.ui.core.ScreenFragment
import ru.blizzed.timetablespbu.ui.screens.search.classrooms.ClassroomsScreen
import ru.blizzed.timetablespbu.ui.screens.educators.EducatorsScreen
import kotlin.reflect.KClass

class SearchTabsAdapter(private val fragment: Fragment) : FragmentPagerAdapter(fragment.childFragmentManager) {

    override fun getItem(position: Int): Fragment = ScreenFragment.instantiate(
            fragment.requireContext(),
            SearchTabs.values()[position].screenClass,
            SearchTabs.values()[position].state
    )

    override fun getCount() = SearchTabs.values().size

    override fun getPageTitle(position: Int): String = fragment.getString(SearchTabs.values()[position].titleRes)

}

enum class SearchTabs(
        val screenClass: KClass<out ScreenFragment<*>>,
        @StringRes val titleRes: Int,
        val state: Parcelable
) {
    EDUCATORS(EducatorsScreen::class, R.string.screen_search_educators_tab_title, NoState),
    CLASSROOMS(ClassroomsScreen::class, R.string.screen_search_classrooms_tab_title, NoState)
}

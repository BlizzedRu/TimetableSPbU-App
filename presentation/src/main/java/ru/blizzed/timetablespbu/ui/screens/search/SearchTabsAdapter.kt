package ru.blizzed.timetablespbu.ui.screens.search

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.core.NoState
import ru.blizzed.timetablespbu.ui.core.Screen
import ru.blizzed.timetablespbu.ui.core.ScreenFragment
import ru.blizzed.timetablespbu.ui.screens.search.classrooms.ClassroomsSearchScreen
import ru.blizzed.timetablespbu.ui.screens.search.educators.EducatorsSearchScreen
import kotlin.reflect.KClass

class SearchTabsAdapter(private val fragment: Fragment) : FragmentPagerAdapter(fragment.childFragmentManager) {

    override fun getItem(position: Int): Fragment = ScreenFragment.newInstance(
        Tabs.values()[position].screenClass,
        Tabs.values()[position].state
    )

    override fun getCount() = Tabs.values().size

    override fun getPageTitle(position: Int): String = fragment.getString(Tabs.values()[position].titleRes)

    private enum class Tabs(
        val screenClass: KClass<out Screen<*>>,
        @StringRes val titleRes: Int,
        val state: Parcelable
    ) {
        EDUCATORS(EducatorsSearchScreen::class, R.string.screen_search_educators_tab_title, NoState),
        CLASSROOMS(ClassroomsSearchScreen::class, R.string.screen_search_classrooms_tab_title, NoState)
    }

}
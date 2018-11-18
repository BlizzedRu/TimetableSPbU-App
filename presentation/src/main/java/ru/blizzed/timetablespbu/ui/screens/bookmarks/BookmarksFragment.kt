package ru.blizzed.timetablespbu.ui.screens.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.common.BaseFragment

class BookmarksFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_bookmarks, container, false)
        return view
    }

}
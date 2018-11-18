package ru.blizzed.timetablespbu.ui.screens.actions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.ui.common.BaseFragment

class SearchFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_search, container, false)
        return view
    }

}
package ru.blizzed.timetablespbu.ui.screens.welcome.educator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.blizzed.timetablespbu.R

class EducatorSelfSelectionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_educator_self_selection, container, false)
    }

}
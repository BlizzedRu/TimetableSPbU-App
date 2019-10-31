package ru.blizzed.timetablespbu.ui.common

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.blizzed.timetablespbu.R

open class FullScreenDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}
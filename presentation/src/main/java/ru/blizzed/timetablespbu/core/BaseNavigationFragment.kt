package ru.blizzed.timetablespbu.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

abstract class BaseNavigationFragment<TActivity : AppCompatActivity> : NavHostFragment(), LayoutResProvider {

    @Suppress("UNCHECKED_CAST")
    val hostActivity: TActivity by lazy {
        requireActivity() as TActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutRes, container, false)
    }

}

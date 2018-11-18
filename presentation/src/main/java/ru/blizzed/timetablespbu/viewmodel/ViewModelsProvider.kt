package ru.blizzed.timetablespbu.viewmodel

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

object ViewModelsProvider {

    fun of(lifecycleOwner: LifecycleOwner): ViewModelProvider =
        getViewModelProvider(lifecycleOwner).viewModelFactory.run {
            when (lifecycleOwner) {
                is Fragment -> ViewModelProviders.of(lifecycleOwner, this)
                is FragmentActivity -> ViewModelProviders.of(lifecycleOwner, this)
                else -> throw IllegalArgumentException("Unknown lifecycle owner.")
            }
        }

    private fun getViewModelProvider(lifecycleOwner: Any?): ViewModelFactoryProvider =
        when (lifecycleOwner) {
            is ViewModelFactoryProvider -> lifecycleOwner
            is Activity -> getViewModelProvider(lifecycleOwner.application)
            is Fragment -> getViewModelProvider(lifecycleOwner.parentFragment ?: lifecycleOwner.activity)
            else -> throw IllegalArgumentException("ViewModel factory provider is unreachable.")
        }

}
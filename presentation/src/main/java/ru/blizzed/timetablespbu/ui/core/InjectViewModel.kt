package ru.blizzed.timetablespbu.ui.core

import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention
annotation class InjectViewModel(val viewModelClass: KClass<out ViewModel>, val lifecycleOwner: LifecycleOwnerType = LifecycleOwnerType.THIS)

enum class LifecycleOwnerType {
    THIS, PARENT, ACTIVITY
}
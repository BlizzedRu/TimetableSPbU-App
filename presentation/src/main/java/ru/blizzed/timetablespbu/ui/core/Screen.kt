package ru.blizzed.timetablespbu.ui.core

import android.content.Intent
import android.os.Parcelable
import android.view.*
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import ru.blizzed.timetablespbu.viewmodel.ViewModelsProvider
import timber.log.Timber
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

abstract class Screen<State : Parcelable>(
    screenContext: ScreenContext
) : LifecycleOwner {

    val fragment: ScreenFragment<State>
    val activity: ScreenActivity
    val state: State
    val view: View

    private val lifecycle = LifecycleRegistry(this)

    init {
        @Suppress("UNCHECKED_CAST")
        fragment = screenContext.fragment as ScreenFragment<State>
        activity = fragment.activity as ScreenActivity
        state = fragment.state
        view = screenContext.run {
            inflater.inflate(layoutRes, root, false)
        }
        injectViewModels()
    }

    override fun getLifecycle() = lifecycle

    open fun onCreate() {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    open fun onStart() {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    open fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    }

    open fun onOptionsItemSelected(item: MenuItem) = false

    open fun onResume() {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    open fun onPause() {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }

    open fun onStop() {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    open fun onDestroy() {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    fun <V : View> findViewById(@IdRes id: Int): V = view.findViewById(id)

    fun setHasOptionsMenu(hasMenu: Boolean) = fragment.setHasOptionsMenu(hasMenu)

    fun getString(@StringRes stringRes: Int) = activity.getString(stringRes)

    fun getString(@StringRes stringRes: Int, vararg args: Any) = activity.getString(stringRes, args)

    fun startActivity(intent: Intent) = fragment.startActivity(intent)

    fun startActivityForResult(intent: Intent, requestCode: Int) = fragment.startActivityForResult(intent, requestCode)

    private fun injectViewModels() {
        javaClass.kotlin.declaredMemberProperties
            // There is an issue with Kotlin's findAnnotation() so use java
            .map { field -> field to field.javaField?.declaredAnnotations?.firstOrNull { it is InjectViewModel } as InjectViewModel? }
            .filter { it -> it.second != null }
            .onEach {
                if (it.first !is KMutableProperty<*>) throw ScreenCreationException("ViewModel property ${it.first.name} must be mutable.")
            }
            .map { it.first as KMutableProperty<*> to it.second!! }
            .forEach {
                with(it.first) {
                    isAccessible = true
                    setter.call(
                        this@Screen,
                        ViewModelsProvider.of(getLifecycleOwnerByType(it.second.lifecycleOwner)).get(it.second.viewModelClass.java)
                    )
                }
                Timber.d("ViewModel ${it.second.viewModelClass.simpleName} successfully injected into ${javaClass.simpleName}.")
            }
    }

    private fun getLifecycleOwnerByType(lifecycleOwnerType: LifecycleOwnerType): LifecycleOwner =
        when (lifecycleOwnerType) {
            LifecycleOwnerType.THIS -> fragment
            LifecycleOwnerType.PARENT -> fragment.parentFragment!!
            LifecycleOwnerType.ACTIVITY -> activity
        }

    data class ScreenContext(
        val fragment: ScreenFragment<*>,
        val inflater: LayoutInflater,
        @LayoutRes val layoutRes: Int,
        val root: ViewGroup?
    )

}
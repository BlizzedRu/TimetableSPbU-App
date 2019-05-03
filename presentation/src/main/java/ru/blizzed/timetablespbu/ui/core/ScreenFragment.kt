package ru.blizzed.timetablespbu.ui.core

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import ru.blizzed.timetablespbu.viewmodel.system.ViewModelsProvider
import timber.log.Timber
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

abstract class ScreenFragment<TState : Parcelable> : Fragment() {

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun <TScreen : ScreenFragment<TState>, TState : Parcelable> create(
                context: Context,
                screenClass: KClass<out ScreenFragment<TState>>,
                state: TState
        ): TScreen = Fragment.instantiate(context, screenClass.java.name, args(state)) as TScreen

        fun instantiate(
                context: Context,
                screenClass: KClass<out ScreenFragment<*>>,
                state: Parcelable?
        ): ScreenFragment<*> = Fragment.instantiate(context, screenClass.java.name, args(state)) as ScreenFragment<*>

        fun args(state: Parcelable?) = bundleOf(STATE_KEY_EXTRA to state)

        private const val STATE_KEY_EXTRA = "STATE_KEY_EXTRA"
    }

    protected lateinit var state: TState

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        state = savedInstanceState?.getParcelable<TState>(STATE_KEY_EXTRA)
                ?: arguments?.getParcelable(STATE_KEY_EXTRA)
                        ?: throw IllegalStateException("State must be not null")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectViewModels()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val params = javaClass.kotlin.findAnnotation<ScreenParams>()
                ?: throw IllegalStateException("Screen fragment must have ${ScreenParams::class.java} annotation.")
        return inflater.inflate(params.layoutRes, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(STATE_KEY_EXTRA, state)
    }

    private fun injectViewModels() {
        javaClass.declaredFields
                .filter { it.isAnnotationPresent(InjectViewModel::class.java) }
                .map { it to it.getAnnotation(InjectViewModel::class.java) }
                .forEach { (field, annotation) ->
                    field.isAccessible = true
                    field.set(
                            this,
                            ViewModelsProvider.of(getLifecycleOwnerByType(annotation.lifecycleOwner)).get(annotation.viewModelClass.java)
                    )
                    Timber.d("ViewModel ${annotation.viewModelClass.simpleName} successfully injected into ${javaClass.simpleName}.")
                }
    }

    private fun getLifecycleOwnerByType(lifecycleOwnerType: LifecycleOwnerType): LifecycleOwner =
            when (lifecycleOwnerType) {
                LifecycleOwnerType.THIS -> this
                LifecycleOwnerType.PARENT -> parentFragment!!
                LifecycleOwnerType.ACTIVITY -> requireActivity()
            }

}
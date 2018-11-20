package ru.blizzed.timetablespbu.ui.core

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.*
import androidx.fragment.app.Fragment
import ru.blizzed.timetablespbu.ui.core.Screen.ScreenContext
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

class ScreenFragment<State : Parcelable> : Fragment() {

    companion object {
        fun <State : Parcelable> newInstance(
            screenClass: KClass<out Screen<out State>>,
            state: Parcelable?
        ): ScreenFragment<State> {
            val args = Bundle().apply {
                putSerializable(ARGS_SCREEN_CLASS_KEY, screenClass.java)
                putParcelable(ARGS_STATE_KEY, state)
            }
            return ScreenFragment<State>().apply { arguments = args }
        }

        private const val ARGS_SCREEN_CLASS_KEY = "screen_class"
        private const val ARGS_STATE_KEY = "state"
    }

    internal lateinit var state: State

    private lateinit var screenClass: KClass<out Screen<State>>

    private lateinit var screen: Screen<State>

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val stateFetched = savedInstanceState?.let {
            state = it.getParcelable(ARGS_STATE_KEY) as State
            true
        } ?: false

        arguments?.apply {
            getSerializable(ARGS_SCREEN_CLASS_KEY)?.let {
                screenClass = (it as Class<Screen<State>>).kotlin
            } ?: throw ScreenCreationException("Property $ARGS_SCREEN_CLASS_KEY not found.")

            if (!stateFetched) state = getParcelable(ARGS_STATE_KEY) as State
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val params = screenClass.findAnnotation<ScreenParams>()
            ?: throw ScreenCreationException("Annotation ${ScreenParams::class.simpleName} in ${screenClass.simpleName} must be set.")

        val screenContext = ScreenContext(this, inflater, params.layoutRes, container)

        screen = createScreen(screenContext)?.apply {
            onCreate()
        } ?: throw ScreenCreationException("Can't create screen ${screenClass.simpleName}.")

        return screen.view
    }

    private fun createScreen(screenContext: ScreenContext): Screen<State>? =
        screenClass.primaryConstructor?.run {
            if (parameters.size != 1) throw ScreenCreationException(
                "Screen class ${screenClass.simpleName} must have only one single constructor with ScreenContext param."
            )
            call(screenContext)
        }

    override fun onStart() {
        super.onStart()
        screen.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        screen.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return screen.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        screen.onResume()
    }

    override fun onPause() {
        super.onPause()
        screen.onPause()
    }

    override fun onStop() {
        super.onStop()
        screen.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(ARGS_STATE_KEY, state)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        screen.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        screen.onActivityResult(requestCode, resultCode, data)
    }

}
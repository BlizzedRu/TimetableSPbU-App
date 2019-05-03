package ru.blizzed.timetablespbu.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.extensions.setOnRippleClickListener
import ru.blizzed.timetablespbu.utils.Event

class DefaultLoadableContentLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LoadableContentLayout(context, attrs, defStyle) {

    private val retryButton: Button = errorView.findViewById(R.id.retryButton)

    fun setOnRetryButtonClickListener(listener: (() -> Unit)?) {
        listener?.let(retryButton::setOnRippleClickListener) ?: retryButton.setOnClickListener(null)
    }

    fun setStatusByEvent(event: Event<*>) {
        status = when (event) {
            is Event.Loading -> Status.LOADING
            is Event.Error -> Status.ERROR
            else -> Status.CONTENT
        }
    }

}

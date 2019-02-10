package ru.blizzed.timetablespbu.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.extensions.isVisibleAnimated
import ru.blizzed.timetablespbu.utils.Event
import kotlin.properties.Delegates

class LoadableContentLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    enum class Status(val statusCode: Int) {
        LOADING(0), ERROR(1), CONTENT(2), EMPTY(3)
    }

    companion object {
        private const val DEFAULT_LOADING_VIEW_LAYOUT_ID = R.layout.common_loading
        private const val DEFAULT_ERROR_VIEW_LAYOUT_ID = R.layout.common_error
    }

    private lateinit var loadingView: View
    private lateinit var errorView: View
    private var emptyView: View? = null

    @IdRes
    private var emptyViewId: Int = View.NO_ID

    private val contentViewIndexes: MutableList<Int> = ArrayList()

    var status: Status by Delegates.observable(Status.CONTENT) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            getStatusViews(oldValue).forEach { it?.isVisibleAnimated = false }
            getStatusViews(newValue).forEach { it?.isVisibleAnimated = true }
        }
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.LoadableContentLayout, 0) {
            val loadingViewLayoutRes = getResourceId(R.styleable.LoadableContentLayout_loadingView, DEFAULT_LOADING_VIEW_LAYOUT_ID)
            val errorViewLayoutRes = getResourceId(R.styleable.LoadableContentLayout_errorView, DEFAULT_ERROR_VIEW_LAYOUT_ID)
            LayoutInflater.from(context).apply {
                // I don't use 'true' as attachToRoot parameter because onViewAdded needs to be called strictly after view initialization
                loadingView = inflate(loadingViewLayoutRes, this@LoadableContentLayout, false)
                addView(loadingView)

                errorView = inflate(errorViewLayoutRes, this@LoadableContentLayout, false)
                addView(errorView)
            }

            emptyViewId = getResourceId(R.styleable.LoadableContentLayout_emptyViewId, View.NO_ID)

            getInt(R.styleable.LoadableContentLayout_status, Status.CONTENT.statusCode).also { code ->
                status = Status.values()[code]
            }
        }
    }

    fun setStatusByEvent(event: Event<*>) {
        when (event) {
            is Event.Loading -> status = Status.LOADING
            is Event.Error -> status = Status.ERROR
        }
    }

    override fun onViewAdded(child: View) {
        super.onViewAdded(child)
        if (child.id != View.NO_ID && child.id == emptyViewId) {
            emptyView = child
        }

        child.isVisible = when {
            ::loadingView.isInitialized && child == loadingView -> status == Status.LOADING
            ::errorView.isInitialized && child == errorView -> status == Status.ERROR
            emptyView != null && child == emptyView -> status == Status.EMPTY
            else -> {
                contentViewIndexes += indexOfChild(child)
                status == Status.CONTENT
            }
        }
    }

    override fun onViewRemoved(child: View) {
        if (child != loadingView && child != emptyView && child != errorView) {
            contentViewIndexes -= indexOfChild(child)
        }
        super.onViewRemoved(child)
    }

    private fun getStatusViews(status: Status): List<View?> =
        when (status) {
            Status.LOADING -> listOf(loadingView)
            Status.ERROR -> listOf(errorView)
            Status.CONTENT -> contentViewIndexes.map(::getChildAt)
            Status.EMPTY -> listOf(emptyView)
        }

}
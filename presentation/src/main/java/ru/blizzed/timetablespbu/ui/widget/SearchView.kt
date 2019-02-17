package ru.blizzed.timetablespbu.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.extensions.hideKeyboard
import ru.blizzed.timetablespbu.extensions.isVisibleAnimated
import ru.blizzed.timetablespbu.extensions.openKeyboard
import ru.blizzed.timetablespbu.extensions.setOnRippleClickListener
import ru.blizzed.timetablespbu.ui.common.DefaultTextWatcher

class SearchView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        @AttrRes defStyleAttr: Int = 0,
        @StyleRes defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val NO_COLOR = -1
    }

    private val textInput: EditText
    private val statusButton: ImageView
    private val closeButton: ImageView
    private val phantomView: View

    private var queryObserverWatcher: TextWatcher? = null

    init {
        inflate(context, R.layout.view_search, this)

        textInput = findViewById(R.id.textInput)
        statusButton = findViewById(R.id.statusButton)
        closeButton = findViewById(R.id.closeButton)
        phantomView = findViewById(R.id.phantomView)

        context.withStyledAttributes(attrs, R.styleable.SearchView, defStyleAttr, defStyleRes) {
            getColor(R.styleable.SearchView_iconsColor, NO_COLOR).takeIf(::isColorSet)?.let(::setIconsColor)

            getDrawable(R.styleable.SearchView_searchBackground).let(::setSearchBackground)

            getColor(R.styleable.SearchView_searchTextColor, NO_COLOR).takeIf(::isColorSet)?.let(::setSearchTextColor)

            getColor(R.styleable.SearchView_searchHintColor, NO_COLOR).takeIf(::isColorSet)?.let(::setSearchHintColor)

            getBoolean(R.styleable.SearchView_disabled, false).let(::setDisabled)
        }

        textInput.addTextChangedListener(object : DefaultTextWatcher() {
            override fun afterTextChanged(text: Editable) {
                text.toString().takeIf(String::isNotBlank)?.let {
                    showSearchCloseButton()
                } ?: hideSearchCloseButton()
            }
        })

        statusButton.setOnRippleClickListener(::openSearch)

        closeButton.setOnRippleClickListener {
            hideSearchCloseButton()
            clearInput()
        }

    }

    fun observe(queryObserver: (String) -> Unit) {
        queryObserverWatcher?.let(textInput::removeTextChangedListener)

        queryObserverWatcher = object : DefaultTextWatcher() {
            override fun afterTextChanged(text: Editable) = queryObserver(text.toString())
        }.also(textInput::addTextChangedListener)
    }

    fun openSearch() = textInput.openKeyboard()

    fun closeSearch() {
        textInput.hideKeyboard()
        if (textInput.text.isNotEmpty()) clearInput()
        closeButton.isVisibleAnimated = false
    }

    fun setIconsColor(@ColorInt color: Int) {
        statusButton.drawable.setTint(color)
        closeButton.drawable.setTint(color)
    }

    fun setSearchBackground(backgroundDrawable: Drawable) {
        background = backgroundDrawable
    }

    fun setSearchTextColor(@ColorInt color: Int) {
        textInput.setTextColor(color)
    }

    fun setSearchHintColor(@ColorInt color: Int) {
        textInput.setHintTextColor(color)
    }

    override fun setOnClickListener(listener: OnClickListener?) = phantomView.setOnClickListener(listener)

    fun setDisabled(isDisabled: Boolean) {
        phantomView.isVisible = isDisabled
    }

    private fun showSearchCloseButton() {
        closeButton.isVisibleAnimated = true
    }

    private fun hideSearchCloseButton() {
        closeButton.isVisibleAnimated = false
    }

    private fun isColorSet(value: Int): Boolean {
        return value != NO_COLOR
    }

    private fun clearInput() {
        textInput.text = null
    }

}
package ru.blizzed.timetablespbu.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.extensions.isVisibleAnimated
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
    private val closeButton : ImageView

    init {
        inflate(context, R.layout.view_search, this)

        textInput = findViewById(R.id.textInput)
        statusButton = findViewById(R.id.statusButton)
        closeButton = findViewById(R.id.closeButton)

        context.withStyledAttributes(attrs, R.styleable.SearchView, defStyleAttr, defStyleRes) {
            getColor(R.styleable.SearchView_iconsColor, NO_COLOR).takeIf(::isColorSet)?.let(::setIconsColor)

            getDrawable(R.styleable.SearchView_searchBackground).let(::setSearchBackground)

            getColor(R.styleable.SearchView_searchTextColor, NO_COLOR).takeIf(::isColorSet)?.let(::setSearchTextColor)

            getColor(R.styleable.SearchView_searchHintColor, NO_COLOR).takeIf(::isColorSet)?.let(::setSearchHintColor)

            getBoolean(R.styleable.SearchView_disabled, false).let(::setDisabled)
        }
    }

    fun observe(queryObserver: (String) -> Unit) {
        textInput.addTextChangedListener(object : DefaultTextWatcher() {
            override fun afterTextChanged(text: Editable) {
                text.toString().apply {
                    if (isNotBlank()) showSearchCloseButton()
                    queryObserver(this)
                }
            }
        })

        statusButton.setOnRippleClickListener(::showSearchSoftInput)

        closeButton.setOnRippleClickListener {
            hideSearchCloseButton()
            textInput.text = null
        }
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

    fun setDisabled(isDisabled: Boolean) {
        setViewDisabled(textInput, isDisabled)
        setViewDisabled(closeButton, isDisabled)
        setViewDisabled(statusButton, isDisabled)
    }

    private fun setViewDisabled(view: View, isDisabled: Boolean) = view.apply {
        isFocusable = !isDisabled
        isEnabled = !isDisabled
    }

    private fun showSearchSoftInput() {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            showSoftInput(textInput, InputMethodManager.SHOW_IMPLICIT)
            textInput.requestFocus()
        }
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

}
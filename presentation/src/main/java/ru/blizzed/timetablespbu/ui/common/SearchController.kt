package ru.blizzed.timetablespbu.ui.common

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.extensions.isVisibleAnimated
import ru.blizzed.timetablespbu.extensions.setOnRippleClickListener

class SearchController(
    private val activity: Activity,
    private val rootView: View
) {

    private val searchInput = rootView.findViewById<EditText>(R.id.searchInput)
    private val searchStatusButton = rootView.findViewById<ImageView>(R.id.searchStatusButton)
    private val searchCloseButton = rootView.findViewById<ImageView>(R.id.searchCloseButton)

    fun observe(queryObserver: (String) -> Unit) {
        searchInput.addTextChangedListener(object : DefaultTextWatcher() {
            override fun afterTextChanged(s: Editable) {
                s.toString().apply {
                    if (isNotBlank()) showSearchCloseButton()
                    queryObserver(this)
                }
            }
        })

        searchStatusButton.setOnRippleClickListener(::showSearchSoftInput)

        searchCloseButton.setOnRippleClickListener {
            hideSearchCloseButton()
            searchInput.text = null
        }
    }

    private fun showSearchSoftInput() {
        (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            showSoftInput(searchInput, InputMethodManager.SHOW_IMPLICIT)
            searchInput.requestFocus()
        }
    }

    private fun showSearchCloseButton() {
        searchCloseButton.isVisibleAnimated = true
    }

    private fun hideSearchCloseButton() {
        searchCloseButton.isVisibleAnimated = false
    }

}
package ru.blizzed.timetablespbu.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import com.google.android.material.textfield.TextInputEditText

/**
 * Custom [EditText] for clearing focus after keyboard hiding (back pressed or done button pressed)
 */
class ClearFocusOnKeyboardBackOrDoneTextInput @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : TextInputEditText(context, attrs) {

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
        // Back keyboard button pressed
        if (keyCode == KeyEvent.KEYCODE_BACK) clearFocus()
        return super.onKeyPreIme(keyCode, event)
    }

    override fun onEditorAction(actionCode: Int) {
        super.onEditorAction(actionCode)
        // Done keyboard button pressed
        if (actionCode == EditorInfo.IME_ACTION_DONE) clearFocus()
    }
}
package ru.blizzed.timetablespbu.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.withStyledAttributes
import ru.blizzed.timetablespbu.R

class CircleTitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    companion object {
        private const val MAX_TITLE_LENGTH = 2
    }

    @ColorInt
    var circleColor: Int = context.resources.getColor(R.color.accent)
        set(value) {
            background.setTint(value)
        }

    init {
        context.resources.getDimensionPixelSize(R.dimen.view_circle_title_size).let {
            width = it
            height = it
        }

        gravity = Gravity.CENTER
        maxLines = 1
        setTextColor(context.resources.getColor(android.R.color.white))
        setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.view_circle_name_text_size))
        isAllCaps = true

        super.setBackgroundDrawable(context.getDrawable(R.drawable.circle))

        context.withStyledAttributes(attrs, R.styleable.CircleTitleView, defStyleAttr) {
            circleColor = getColor(R.styleable.CircleTitleView_circleColor, circleColor)
        }
    }

    override fun setText(text: CharSequence?, type: BufferType?) =
        super.setText(text?.subSequence(0, Math.min(text.length, MAX_TITLE_LENGTH)), type)

    override fun setBackground(background: Drawable?) = Unit

    override fun setBackgroundColor(color: Int) = Unit

    override fun setBackgroundResource(resId: Int) = Unit

    override fun setBackgroundDrawable(background: Drawable?) = Unit

}

package ru.blizzed.timetablespbu.ui.screens.common.group_search

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.annotation.AttrRes
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.view_selected_groups.view.chipGroup
import kotlinx.android.synthetic.main.view_selected_groups.view.scroll
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.extensions.inflateAsParent

class SelectedGroupsView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

  var onRemoveClickListener: ((Group) -> Unit)? = null

  init {
    inflate(context, R.layout.view_selected_groups, this)
  }

  fun setGroups(groups: List<Group>) {
    val isDeletion = chipGroup.childCount > groups.size

    chipGroup.removeAllViews()
    groups
      .map { group ->
        chipGroup.inflateAsParent<Chip>(R.layout.item_selected_group).apply {
          text = group.alias
          setOnCloseIconClickListener { onRemoveClickListener?.invoke(group) }
          isCheckable = false
          isClickable = false
        }
      }
      .forEach(chipGroup::addView)

    if (!isDeletion) {
      scroll.post {
        scroll.fullScroll(ScrollView.FOCUS_RIGHT)
      }
    }
  }

}

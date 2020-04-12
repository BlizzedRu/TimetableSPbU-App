package ru.blizzed.timetablespbu.ui.screens.common.group_search.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_timetable_browse_admission_year_groups.admissionYearTitle
import kotlinx.android.synthetic.main.item_timetable_browse_admission_year_groups.groupsChipGroup
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.AdmissionYearGroups
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.extensions.getString
import ru.blizzed.timetablespbu.extensions.inflate
import ru.blizzed.timetablespbu.ui.common.adapters.BaseSingleTypeListAdapter
import ru.blizzed.timetablespbu.ui.common.adapters.SimpleDiffCallback
import ru.blizzed.timetablespbu.ui.screens.common.group_search.group.AdmissionYearGroupsAdapter.ViewHolder

typealias OnGroupClickListener = (Group) -> Unit

class AdmissionYearGroupsAdapter(
  private val listener: OnGroupClickListener
) : BaseSingleTypeListAdapter<AdmissionYearGroups, ViewHolder>(DIFF_CALLBACK) {

  companion object {
    val DIFF_CALLBACK = object : SimpleDiffCallback<AdmissionYearGroups>() {
      override fun areItemsTheSame(oldItem: AdmissionYearGroups, newItem: AdmissionYearGroups): Boolean =
        oldItem.id == newItem.id
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
    containerView = LayoutInflater.from(parent.context).inflate(R.layout.item_timetable_browse_admission_year_groups, parent, false),
    listener = listener
  )

  override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>) {
    super.onBindViewHolder(holder, position, payloads)
    holder.bind(getItems()[holder.adapterPosition])
  }

  class ViewHolder(
    override val containerView: View,
    private val listener: OnGroupClickListener
  ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(model: AdmissionYearGroups) {
      admissionYearTitle.text = getString(
        R.string.welcome_student_group_search_step_group_admission_year_pattern,
        model.admissionYear.year.toString()
      )
      groupsChipGroup.removeAllViews()
      model.groups
        .map(::createChipFor)
        .forEach(groupsChipGroup::addView)
    }

    private fun createChipFor(group: Group) = inflate<Chip>(R.layout.item_timetable_browse_group).apply {
      text = group.alias
      setOnClickListener { listener.invoke(group) }
    }

  }

}

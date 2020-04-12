package ru.blizzed.timetablespbu.ui.screens.common.group_search.admission_year

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_program_combination.admissionYearsChipGroup
import kotlinx.android.synthetic.main.item_program_combination.name
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.extensions.inflate
import ru.blizzed.timetablespbu.ui.common.adapters.BaseSingleTypeListAdapter
import ru.blizzed.timetablespbu.ui.common.adapters.SimpleDiffCallback
import ru.blizzed.timetablespbu.ui.screens.common.group_search.admission_year.StudyProgramCombinationsAdapter.ViewHolder

typealias OnAdmissionYearClickListener = (AdmissionYear) -> Unit

class StudyProgramCombinationsAdapter(
    private val listener: OnAdmissionYearClickListener
) : BaseSingleTypeListAdapter<StudyProgramCombination, ViewHolder>(DIFF_CALLBACK) {

  companion object {
    val DIFF_CALLBACK = object : SimpleDiffCallback<StudyProgramCombination>() {
      override fun areItemsTheSame(oldItem: StudyProgramCombination, newItem: StudyProgramCombination): Boolean =
          oldItem.name == newItem.name
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
    containerView = LayoutInflater.from(parent.context).inflate(R.layout.item_program_combination, parent, false),
      listener = listener
  )

  override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: List<Any>) {
    super.onBindViewHolder(holder, position, payloads)
    holder.bind(getItems()[holder.adapterPosition])
  }

  class ViewHolder(
      override val containerView: View,
      private val listener: OnAdmissionYearClickListener
  ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(combination: StudyProgramCombination) {
        name.text = combination.name
        admissionYearsChipGroup.removeAllViews()
        combination.admissionYears.forEach { year ->
            admissionYearsChipGroup.addView(createChipFor(year))
        }
    }

      private fun createChipFor(year: AdmissionYear) = inflate<Chip>(R.layout.item_admission_year).apply {
          text = year.year.toString()
          setOnClickListener { listener.invoke(year) }
      }

  }

}

package ru.blizzed.timetablespbu.ui.screens.search.educators

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_educator_search.view.*
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.EducatorEntity

class EducatorsSearchAdapter : RecyclerView.Adapter<EducatorsSearchAdapter.EducatorViewHolder>() {

    var aducators: List<EducatorEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducatorViewHolder =
        EducatorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_educator_search, parent, false))

    override fun getItemCount() = aducators.size

    override fun onBindViewHolder(holder: EducatorViewHolder, position: Int) = holder.bind(aducators[position])

    class EducatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(educator: EducatorEntity) {
            itemView.educatorName.text = educator.fullName
            itemView.educatorEmployment.text = educator.employments.firstOrNull()?.post ?: ""
        }

    }

}
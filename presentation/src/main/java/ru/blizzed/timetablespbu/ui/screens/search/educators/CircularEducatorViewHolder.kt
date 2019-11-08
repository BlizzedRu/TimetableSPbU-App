package ru.blizzed.timetablespbu.ui.screens.search.educators

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_educator_circular.view.*
import ru.blizzed.timetablespbu.domain.entities.Educator
import ru.blizzed.timetablespbu.extensions.getShortInitials
import ru.blizzed.timetablespbu.ui.widget.CircleTitleView

class CircularEducatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val circleTitle: CircleTitleView = itemView.educatorTitle
    private val lastName: TextView = itemView.educatorLastName
    private val initials: TextView = itemView.educatorInitials

    fun bind(
        educator: Educator,
        onEducatorClickListener: ((Educator) -> Unit)?
    ) {
        circleTitle.text = educator.fullName
        circleTitle.circleColor = educator.circleColor

        educator.getNameComponents().also {
            lastName.text = it.lastName
            initials.text = it.getShortInitials()
        }

        itemView.setOnClickListener {
            onEducatorClickListener?.invoke(educator)
        }
    }

}

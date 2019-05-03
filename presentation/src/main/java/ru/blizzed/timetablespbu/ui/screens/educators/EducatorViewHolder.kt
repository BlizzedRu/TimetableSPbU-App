package ru.blizzed.timetablespbu.ui.screens.educators

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_educator.view.educatorEmployment
import kotlinx.android.synthetic.main.item_list_educator.view.educatorName
import kotlinx.android.synthetic.main.item_list_educator.view.favoriteButton
import kotlinx.android.synthetic.main.item_list_educator.view.favoriteCheckBox
import ru.blizzed.timetablespbu.domain.entities.Educator

class EducatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        educator: Educator,
        onEducatorClickListener: ((Educator) -> Unit)?,
        onFavoriteClickListener: ((Educator) -> Unit)? = null,
        showFavorite: Boolean = onFavoriteClickListener != null
    ) {
        itemView.educatorName.text = educator.fullName
        itemView.educatorEmployment.text = educator.employments.firstOrNull()?.run {
            "$post, $department"
        }

        itemView.favoriteButton.isVisible = showFavorite

        itemView.favoriteCheckBox.isChecked = educator.isFavorite

        itemView.setOnClickListener {
            onEducatorClickListener?.invoke(educator)
        }

        itemView.favoriteButton.setOnClickListener {
            itemView.favoriteCheckBox.toggle()
            onFavoriteClickListener?.invoke(educator)
        }
    }

}

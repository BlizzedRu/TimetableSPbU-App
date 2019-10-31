package ru.blizzed.timetablespbu.ui.screens.faculties

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_faculty.view.*
import ru.blizzed.timetablespbu.R
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.ui.common.adapters.BaseSingleTypeListAdapter
import ru.blizzed.timetablespbu.ui.common.adapters.SimpleDiffCallback
import ru.blizzed.timetablespbu.ui.screens.faculties.FacultiesAdapter.FacultyViewHolder

class FacultiesAdapter : BaseSingleTypeListAdapter<Faculty, FacultyViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : SimpleDiffCallback<Faculty>() {
            override fun areItemsTheSame(oldItem: Faculty, newItem: Faculty): Boolean =
                oldItem.alias == newItem.alias
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder =
        FacultyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_faculty, parent, false))

    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int, payloads: List<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        holder.bind(getItems()[position])
    }

    class FacultyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(faculty: Faculty) {
            itemView.name.text = faculty.name
            itemView.logo.setImageDrawable(getDrawableFromAsset(itemView.context, faculty.logo))
        }

        private fun getDrawableFromAsset(context: Context, pathInAssets: String) = context
            .assets
            .open(pathInAssets)
            .let { inputStream ->
                Drawable.createFromStream(inputStream, null)
            }

    }

}

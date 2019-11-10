package ru.blizzed.timetablespbu.ui.screens.welcome.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.entities.Group

class GroupSelectionSharedViewModel : ViewModel() {

    private val facultyLiveData: MutableLiveData<Faculty> = MutableLiveData()
    private val groupLiveData: MutableLiveData<Group> = MutableLiveData()

    val faculty: LiveData<Faculty> = facultyLiveData

    val group: LiveData<Group> = groupLiveData

    fun dispatchEvent(event: ViewEvent) {
        if (event is ViewEvent.FacultySelected) {
            facultyLiveData.value = event.faculty
        } else if (event is ViewEvent.GroupSelected) {
            groupLiveData.value = event.group
        }
    }

}

sealed class ViewEvent {
    class FacultySelected(val faculty: Faculty) : ViewEvent()
    class GroupSelected(val group: Group) : ViewEvent()
}

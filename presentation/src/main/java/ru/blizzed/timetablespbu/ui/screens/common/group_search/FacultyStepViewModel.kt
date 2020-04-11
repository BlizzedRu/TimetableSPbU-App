package ru.blizzed.timetablespbu.ui.screens.common.group_search

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.usecases.GroupSearchUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class FacultyStepViewModel(
        private val groupSearchUseCase: GroupSearchUseCase
) : BaseSelectionStepViewModel<Faculty, Faculty, Any>() {

    override fun loadItems(param: Any): Single<List<Faculty>> = groupSearchUseCase.getAllFaculties()

}

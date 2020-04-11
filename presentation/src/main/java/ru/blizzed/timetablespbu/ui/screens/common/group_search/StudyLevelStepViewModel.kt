package ru.blizzed.timetablespbu.ui.screens.common.group_search

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Faculty
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.usecases.GroupSearchUseCase
import ru.blizzed.timetablespbu.ui.screens.common.group_search.base.BaseSelectionStepViewModel

class StudyLevelStepViewModel(
        private val groupSearchUseCase: GroupSearchUseCase
) : BaseSelectionStepViewModel<StudyLevel, StudyLevel, Faculty>() {

    override fun loadItems(param: Faculty): Single<List<StudyLevel>> = groupSearchUseCase.getStudyLevelsByDivisionAlias(param.alias)

}

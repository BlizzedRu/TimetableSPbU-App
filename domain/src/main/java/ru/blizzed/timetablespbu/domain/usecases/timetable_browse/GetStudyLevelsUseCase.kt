package ru.blizzed.timetablespbu.domain.usecases.timetable_browse

import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.repositories.TimetableBrowseRepository

class GetStudyLevelsUseCase(private val timetableBrowseRepository: TimetableBrowseRepository) {

  operator fun invoke(facultyAlias: FacultyAlias) = timetableBrowseRepository.getStudyLevelsByDivisionAlias(facultyAlias)

}

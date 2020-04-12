package ru.blizzed.timetablespbu.domain.usecases.timetable_browse

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.AdmissionYearGroups
import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.StudyProgramId
import ru.blizzed.timetablespbu.domain.repositories.TimetableBrowseRepository

class GetAdmissionYearGroupsUseCase(private val timetableBrowseRepository: TimetableBrowseRepository) {

  operator fun invoke(
    facultyAlias: FacultyAlias,
    studyLevelId: Int,
    programId: StudyProgramId
  ): Single<List<AdmissionYearGroups>> = timetableBrowseRepository.getAdmissionYearGroups(facultyAlias, studyLevelId, programId)
    .map { it.filter { it.groups.isNotEmpty() } }

}

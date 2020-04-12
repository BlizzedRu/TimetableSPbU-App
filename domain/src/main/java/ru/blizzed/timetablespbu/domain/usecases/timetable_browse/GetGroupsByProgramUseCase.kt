package ru.blizzed.timetablespbu.domain.usecases.timetable_browse

import ru.blizzed.timetablespbu.domain.entities.StudyProgramId
import ru.blizzed.timetablespbu.domain.repositories.TimetableBrowseRepository

class GetGroupsByProgramUseCase(private val timetableBrowseRepository: TimetableBrowseRepository) {

  operator fun invoke(programId: StudyProgramId) = timetableBrowseRepository.getGroupsByProgramId(programId)

}

package ru.blizzed.timetablespbu.domain.usecases

import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.repositories.FacultiesRepository
import ru.blizzed.timetablespbu.domain.repositories.GroupSearchRepository

class GroupSearchUseCase(
        private val groupSearchRepository: GroupSearchRepository,
        private val facultiesRepository: FacultiesRepository
) {

    fun getAllFaculties() = facultiesRepository.getAll()

    fun getStudyLevelsByDivisionAlias(divisionAlias: String) = groupSearchRepository.getStudyLevelsByDivisionAlias(divisionAlias)

    fun getStudyProgramCombinationsByLevel(studyLevel: StudyLevel) = Single.just(studyLevel.studyProgramCombinations)

    fun getGroupsByAdmissionYear(admissionYear: AdmissionYear) = groupSearchRepository.getGroupsByProgramId(admissionYear.studyProgramId)

}

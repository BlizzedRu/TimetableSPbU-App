package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.AdmissionYear
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import ru.blizzed.timetablespbu.domain.entities.StudyProgramCombination
import ru.blizzed.timetablespbu.domain.mappers.AdmissionYearMapper.mapToEntity
import ru.blizzed.timetablespbu.domain.mappers.StudyProgramCombinationMapper.mapToEntity
import ru.blizzed.timetablespbulib.model.divisions.StudyLevel as ApiStudyLevel
import ru.blizzed.timetablespbulib.model.divisions.StudyProgramCombination as ApiStudyProgramCombination
import ru.blizzed.timetablespbulib.model.divisions.StudyProgramCombination.AdmissionYear as ApiAdmissionYear

object StudyLevelMapper : Mapper<ApiStudyLevel, StudyLevel> {
    override fun ApiStudyLevel.mapToEntity(): StudyLevel = StudyLevel(studyLevelName, studyProgramCombinations.mapToEntity())
}

object AdmissionYearMapper : Mapper<ApiAdmissionYear, AdmissionYear> {
    override fun ApiAdmissionYear.mapToEntity(): AdmissionYear = AdmissionYear(studyProgramId, yearNumber)
}

object StudyProgramCombinationMapper : Mapper<ApiStudyProgramCombination, StudyProgramCombination> {
    override fun ApiStudyProgramCombination.mapToEntity(): StudyProgramCombination = StudyProgramCombination(name, admissionYears.mapToEntity())


}

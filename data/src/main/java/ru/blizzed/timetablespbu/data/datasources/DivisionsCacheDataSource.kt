package ru.blizzed.timetablespbu.data.datasources

import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.StudyLevel
import java.util.concurrent.ConcurrentHashMap

class DivisionsCacheDataSource {

  private val cache: ConcurrentHashMap<FacultyAlias, List<StudyLevel>> = ConcurrentHashMap()

  fun put(facultyAlias: FacultyAlias, levels: List<StudyLevel>) {
    cache[facultyAlias] = ArrayList(levels)
  }

  fun getOrNull(facultyAlias: FacultyAlias) = cache[facultyAlias]

  fun get(facultyAlias: FacultyAlias) = cache.getValue(facultyAlias)

}

package ru.blizzed.timetablespbu.data.datasources

import ru.blizzed.timetablespbu.data.datasources.base.MappedListCacheDataSource
import ru.blizzed.timetablespbu.domain.entities.FacultyAlias
import ru.blizzed.timetablespbu.domain.entities.StudyLevel

class DivisionsCacheDataSource : MappedListCacheDataSource<FacultyAlias, StudyLevel>()

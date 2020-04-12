package ru.blizzed.timetablespbu.data.datasources

import ru.blizzed.timetablespbu.data.datasources.base.MappedListCacheDataSource
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.entities.StudyProgramId

class GroupsCacheDataSource : MappedListCacheDataSource<StudyProgramId, Group>()

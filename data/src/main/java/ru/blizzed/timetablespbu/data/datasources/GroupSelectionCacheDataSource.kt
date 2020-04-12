package ru.blizzed.timetablespbu.data.datasources

import ru.blizzed.timetablespbu.data.datasources.base.SimpleCacheDataSource
import ru.blizzed.timetablespbu.domain.entities.Group

class GroupSelectionCacheDataSource : SimpleCacheDataSource<List<Group>>()

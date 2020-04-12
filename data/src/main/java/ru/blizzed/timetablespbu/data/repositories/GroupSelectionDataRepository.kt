package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Observable
import ru.blizzed.timetablespbu.data.datasources.GroupSelectionCacheDataSource
import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.repositories.GroupSelectionRepository

class GroupSelectionDataRepository(
  private val cacheDataSource: GroupSelectionCacheDataSource
): GroupSelectionRepository {

  override fun addGroup(group: Group) {
    if (cacheDataSource.hasValue()) {
      val modifiedList = cacheDataSource.get().toMutableList().also { it.add(group) }
      cacheDataSource.put(modifiedList)
    } else {
      cacheDataSource.put(listOf(group))
    }
  }

  override fun removeGroup(group: Group) {
    if (cacheDataSource.hasValue()) {
      val modifiedList = cacheDataSource.get().toMutableList().also { it.remove(group) }
      cacheDataSource.put(modifiedList)
    }
  }

  override fun getAllGroups(): List<Group> = cacheDataSource.getOrNull() ?: emptyList()

  override fun observeGroups(): Observable<List<Group>> = cacheDataSource.observe()

}

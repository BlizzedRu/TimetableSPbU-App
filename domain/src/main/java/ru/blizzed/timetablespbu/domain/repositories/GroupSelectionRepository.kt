package ru.blizzed.timetablespbu.domain.repositories

import io.reactivex.Observable
import ru.blizzed.timetablespbu.domain.entities.Group

interface GroupSelectionRepository {

  fun addGroup(group: Group)

  fun removeGroup(group: Group)

  fun getAllGroups(): List<Group>

  fun observeGroups(): Observable<List<Group>>

}

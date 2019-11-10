package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.Group
import ru.blizzed.timetablespbu.domain.mappers.ProgramGroupsMapper.mapToEntity
import ru.blizzed.timetablespbulib.model.groups.ProgramGroups
import ru.blizzed.timetablespbulib.model.groups.Group as ApiGroup

object GroupMapper : Mapper<ProgramGroups, List<Group>> {
    override fun ProgramGroups.mapToEntity(): List<Group> = groups.map { it.mapToEntity() }
}

object ProgramGroupsMapper : Mapper<ApiGroup, Group> {
    override fun ApiGroup.mapToEntity(): Group = Group(
            id = studentGroupId,
            alias = studentGroupName,
            profileName = studentGroupProfiles
    )
}

package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.Address

object AddressMapper : Mapper<ru.blizzed.timetablespbulib.model.addresses.Address, Address> {

    override fun ru.blizzed.timetablespbulib.model.addresses.Address.mapToEntity(): Address = Address(oid, displayName)

}

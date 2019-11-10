package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.Address
import ru.blizzed.timetablespbulib.model.addresses.Address as ApiAddress

object AddressMapper : Mapper<ApiAddress, Address> {

    override fun ApiAddress.mapToEntity(): Address = Address(oid, displayName)

}

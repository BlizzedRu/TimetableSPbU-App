package ru.blizzed.timetablespbu.domain.mappers

import ru.blizzed.timetablespbu.domain.entities.Address
import javax.inject.Inject

class AddressMapper @Inject constructor() : Mapper<ru.blizzed.timetablespbulib.model.addresses.Address, Address> {

    override fun apply(input: ru.blizzed.timetablespbulib.model.addresses.Address): Address = Address(
            input.oid,
            input.displayName
    )

}

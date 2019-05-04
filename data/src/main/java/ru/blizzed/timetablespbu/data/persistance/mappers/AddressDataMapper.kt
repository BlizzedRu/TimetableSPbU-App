package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.AddressData
import ru.blizzed.timetablespbu.domain.entities.Address

class AddressDataMapper : RxDataMapper<Address, AddressData> {

    override fun mapToEntity(input: Address): AddressData = AddressData(
            input.oid,
            input.name,
            input.isFavorite,
            input.isViewed,
            input.lastInteractionTime
    )

    override fun mapToData(input: AddressData): Address = Address(
            input.oid,
            input.name,
            input.isFavorite,
            input.isViewed,
            input.lastInteractionTime
    )

}

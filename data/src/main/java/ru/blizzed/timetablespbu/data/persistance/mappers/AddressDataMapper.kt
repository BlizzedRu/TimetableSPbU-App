package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.AddressData
import ru.blizzed.timetablespbu.domain.entities.Address

class AddressDataMapper : RxDataMapper<AddressData, Address> {

    override fun mapToData(input: Address): AddressData = AddressData(
            input.oid,
            input.name,
            input.isFavorite,
            input.isViewed,
            input.lastInteractionTime
    )

    override fun mapToEntity(input: AddressData): Address = Address(
            input.oid,
            input.name,
            input.isFavorite,
            input.isViewed,
            input.lastInteractionTime
    )

}

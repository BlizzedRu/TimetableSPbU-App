package ru.blizzed.timetablespbu.data.persistance.mappers

import ru.blizzed.timetablespbu.data.persistance.entities.AddressData
import ru.blizzed.timetablespbu.domain.entities.Address

object AddressDataMapper : RxDataMapper<AddressData, Address> {

    override fun Address.mapToData(): AddressData = AddressData(oid, name, isFavorite, isViewed, lastInteractionTime)

    override fun AddressData.mapToEntity(): Address = Address(oid, name, isFavorite, isViewed, lastInteractionTime)

}

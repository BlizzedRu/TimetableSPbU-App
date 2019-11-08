package ru.blizzed.timetablespbu.data.datasources

import ru.blizzed.timetablespbu.data.extensions.executeAsync
import ru.blizzed.timetablespbu.domain.mappers.AddressMapper.mapToEntity
import ru.blizzed.timetablespbulib.methods.AddressesApiMethod

class AddressesRemoteDataSource(
        private val addressesApi: AddressesApiMethod
) {

    fun getAll() = addressesApi
            .all
            .executeAsync()
            .map { it.mapToEntity() }

}

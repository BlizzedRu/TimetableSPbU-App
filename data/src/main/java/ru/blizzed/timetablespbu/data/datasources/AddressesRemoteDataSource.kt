package ru.blizzed.timetablespbu.data.datasources

import ru.blizzed.timetablespbu.data.extensions.executeAsync
import ru.blizzed.timetablespbu.domain.mappers.AddressMapper
import ru.blizzed.timetablespbulib.methods.AddressesApiMethod
import javax.inject.Inject

class AddressesRemoteDataSource @Inject constructor(
        private val addressesApi: AddressesApiMethod,
        private val addressMapper: AddressMapper
){

    fun getAll() = addressesApi
            .all
            .executeAsync()
            .map(addressMapper::apply)

}

package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import ru.blizzed.timetablespbu.data.DBScheduler
import ru.blizzed.timetablespbu.data.persistance.dao.AddressesDao
import ru.blizzed.timetablespbu.data.persistance.entities.AddressData
import ru.blizzed.timetablespbu.data.persistance.mappers.AddressDataMapper
import ru.blizzed.timetablespbu.data.persistance.mappers.RxDataMapper
import ru.blizzed.timetablespbu.domain.entities.Address
import javax.inject.Inject

class AddressesLocalDataSource @Inject constructor(
        private val addressesDao: AddressesDao,
        private val addressMapper: AddressDataMapper,
        @DBScheduler dbScheduler: Scheduler
) : DatabaseDataSource(dbScheduler), RxDataMapper<AddressData, Address> by addressMapper {

    fun getAll(query: String): Single<List<Address>> = addressesDao
            .getAll(query)
            .mapToEntity()
            .onDbScheduler()

    fun observeAll(): Flowable<List<Address>> = addressesDao
            .observeAll()
            .mapToEntity()
            .onDbScheduler()

    fun observeFavorites(): Flowable<List<Address>> = addressesDao
            .observeFavorites()
            .mapToEntity()
            .onDbScheduler()

    fun observeViewed(): Flowable<List<Address>> = addressesDao
            .observeFavorites()
            .mapToEntity()
            .onDbScheduler()

    fun observeNonFavoriteViewed(): Flowable<List<Address>> = addressesDao
            .observeNonFavoriteViewed()
            .mapToEntity()
            .onDbScheduler()

    fun update(address: Address): Completable = Completable
            .fromAction { addressesDao.update(addressMapper.mapToData(address)) }
            .onDbScheduler()

    fun insert(addresses: List<Address>): Completable = Completable
            .fromAction { addressesDao.insert(addressMapper.mapToData(addresses)) }
            .onDbScheduler()

    fun delete(address: Address): Completable = Completable
            .fromAction { addressesDao.delete(addressMapper.mapToData(address)) }
            .onDbScheduler()

    fun isFavorite(address: Address): Single<Boolean> = addressesDao
            .isFavorite(address.oid)
            .onDbScheduler()

    fun getFavorites(): Maybe<List<Address>> = addressesDao
            .getFavorites()
            .mapToEntity()
            .onDbScheduler()

    fun getViewed(): Maybe<List<Address>> = addressesDao
            .getViewed()
            .mapToEntity()
            .onDbScheduler()

}

package ru.blizzed.timetablespbu.data.datasources

import io.reactivex.*
import ru.blizzed.timetablespbu.data.persistance.dao.AddressesDao
import ru.blizzed.timetablespbu.data.persistance.mappers.AddressDataMapper.mapToData
import ru.blizzed.timetablespbu.data.persistance.mappers.AddressDataMapper.mapToEntity
import ru.blizzed.timetablespbu.domain.entities.Address

class AddressesLocalDataSource(
        private val addressesDao: AddressesDao,
        dbScheduler: Scheduler
) : DatabaseDataSource(dbScheduler) {

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
            .fromAction { addressesDao.update(address.mapToData()) }
            .onDbScheduler()

    fun insert(addresses: List<Address>): Completable = Completable
            .fromAction { addressesDao.insert(addresses.mapToData()) }
            .onDbScheduler()

    fun delete(address: Address): Completable = Completable
            .fromAction { addressesDao.delete(address.mapToData()) }
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

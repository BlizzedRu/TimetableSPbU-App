package ru.blizzed.timetablespbu.data.repositories

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.blizzed.timetablespbu.data.datasources.AddressesLocalDataSource
import ru.blizzed.timetablespbu.data.datasources.AddressesRemoteDataSource
import ru.blizzed.timetablespbu.data.utils.TimeUtils
import ru.blizzed.timetablespbu.domain.entities.Address
import ru.blizzed.timetablespbu.domain.repositories.AddressesRepository

class AddressesDataRepository(
        private val localDataSource: AddressesLocalDataSource,
        private val remoteDataSource: AddressesRemoteDataSource,
        private val timeUtils: TimeUtils
) : AddressesRepository {

    override fun observeFavorites(): Flowable<List<Address>> = localDataSource.observeFavorites()

    override fun observeNonFavorites(): Flowable<List<Address>> = localDataSource
            .observeAll()
            .map { addresses ->
                addresses
                        .filter { address -> address.isViewed && !address.isFavorite }
                        .sortedByDescending(Address::lastInteractionTime)
            }

    override fun search(query: String): Single<List<Address>> = localDataSource.getAll(query)

    override fun isFavorite(address: Address): Single<Boolean> = localDataSource.isFavorite(address)

    override fun getFavorites(): Maybe<List<Address>> = localDataSource.getFavorites()

    override fun setFavorite(address: Address, isFavorite: Boolean): Single<Address> = localDataSource
            .update(address.also { it.setFavorite(isFavorite, timeUtils.getCurrentTime()) })
            .andThen(Single.just(address))

    override fun getViewed(): Maybe<List<Address>> = localDataSource.getViewed()

    override fun setViewed(address: Address): Single<Address> = localDataSource
            .update(address.also { it.view(timeUtils.getCurrentTime()) })
            .andThen(Single.just(address))

    override fun fetch(): Completable = remoteDataSource
            .getAll()
            .flatMapCompletable(localDataSource::insert)

}

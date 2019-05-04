package ru.blizzed.timetablespbu.domain.repositories

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.blizzed.timetablespbu.domain.entities.Address

interface AddressesRepository {

    fun observeFavorites(): Flowable<List<Address>>

    fun observeNonFavorites(): Flowable<List<Address>>

    fun search(query: String): Single<List<Address>>

    fun isFavorite(address: Address): Single<Boolean>

    fun getFavorites(): Maybe<List<Address>>

    fun setFavorite(address: Address, isFavorite: Boolean): Single<Address>

    fun getViewed(): Maybe<List<Address>>

    fun setViewed(address: Address): Single<Address>

    fun fetch(): Completable

}

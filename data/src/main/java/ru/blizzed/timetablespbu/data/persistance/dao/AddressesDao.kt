package ru.blizzed.timetablespbu.data.persistance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.blizzed.timetablespbu.data.persistance.entities.AddressData

@Dao
abstract class AddressesDao {

    @Query("SELECT * FROM addresses ORDER BY lastInteractionTime DESC")
    abstract fun observeAll(): Flowable<List<AddressData>>

    @Query("SELECT * FROM addresses WHERE name LIKE '%'||:query||'%' ORDER BY lastInteractionTime DESC")
    abstract fun getAll(query: String): Single<List<AddressData>>

    @Query("SELECT * FROM addresses WHERE isFavorite ORDER BY lastInteractionTime DESC")
    abstract fun observeFavorites(): Flowable<List<AddressData>>

    @Query("SELECT * FROM addresses WHERE isViewed ORDER BY lastInteractionTime DESC")
    abstract fun observeViewed(): Flowable<List<AddressData>>

    @Query("SELECT * FROM addresses WHERE oid = :addressOid LIMIT 1")
    abstract fun getById(addressOid: String): AddressData?

    @Query("SELECT * FROM addresses WHERE isFavorite ORDER BY lastInteractionTime DESC")
    abstract fun getFavorites(): Maybe<List<AddressData>>

    @Query("SELECT * FROM addresses WHERE isViewed ORDER BY lastInteractionTime DESC")
    abstract fun getViewed(): Maybe<List<AddressData>>

    @Query("SELECT COUNT(*) FROM addresses WHERE oid = :addressOid AND isFavorite LIMIT 1 > 0")
    abstract fun isFavorite(addressOid: String): Single<Boolean>

    @Query("SELECT COUNT(*) FROM addresses WHERE oid = :addressOid AND isViewed LIMIT 1 > 0")
    abstract fun isViewed(addressOid: String): Single<Boolean>

    @Update
    abstract fun update(address: AddressData): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(address: AddressData): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(addresses: List<AddressData>): Long

    @Delete
    abstract fun delete(address: AddressData): Int

}

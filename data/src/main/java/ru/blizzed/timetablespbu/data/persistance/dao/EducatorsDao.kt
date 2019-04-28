package ru.blizzed.timetablespbu.data.persistance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import ru.blizzed.timetablespbu.data.persistance.entities.EducatorData

@Dao
abstract class EducatorsDao {

    @Query("SELECT * FROM educators ORDER BY lastInteractionTime DESC")
    abstract fun observeAll(): Flowable<List<EducatorData>>

    @Query("SELECT * FROM educators WHERE fullName LIKE '%'||:query||'%' ORDER BY lastInteractionTime DESC")
    abstract fun getAll(query: String): Single<List<EducatorData>>

    @Query("SELECT * FROM educators WHERE isFavorite ORDER BY lastInteractionTime DESC")
    abstract fun observeFavorites(): Flowable<List<EducatorData>>

    @Query("SELECT * FROM educators WHERE isViewed ORDER BY lastInteractionTime DESC")
    abstract fun observeViewed(): Flowable<List<EducatorData>>

    @Query("SELECT * FROM educators WHERE id = :educatorId LIMIT 1")
    abstract fun getById(educatorId: Int): EducatorData?

    @Query("SELECT * FROM educators WHERE isFavorite ORDER BY lastInteractionTime DESC")
    abstract fun getFavorites(): Maybe<List<EducatorData>>

    @Query("SELECT * FROM educators WHERE isViewed ORDER BY lastInteractionTime DESC")
    abstract fun getViewed(): Maybe<List<EducatorData>>

    @Query("SELECT COUNT(*) FROM educators WHERE id = :educatorId AND isFavorite LIMIT 1 > 0")
    abstract fun isFavorite(educatorId: Int): Single<Boolean>

    @Query("SELECT COUNT(*) FROM educators WHERE id = :educatorId AND isViewed LIMIT 1 > 0")
    abstract fun isViewed(educatorId: Int): Single<Boolean>

    @Update
    abstract fun update(educator: EducatorData): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(educator: EducatorData): Long

    @Delete
    abstract fun delete(educator: EducatorData): Int

    @Transaction
    open fun updateOrAdd(educator: EducatorData) {
        if (getById(educator.id) != null) update(educator) else insert(educator)
    }

}

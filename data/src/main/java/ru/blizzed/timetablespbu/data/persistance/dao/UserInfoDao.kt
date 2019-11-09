package ru.blizzed.timetablespbu.data.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe
import ru.blizzed.timetablespbu.data.persistance.entities.UserInfoData

@Dao
abstract class UserInfoDao {

    @Query("SELECT NOT count(1) WHERE EXISTS (SELECT * FROM user_info)")
    abstract fun isEmpty(): Boolean

    @Query("SELECT * FROM user_info LIMIT 1")
    abstract fun get(): Maybe<UserInfoData>

    @Query("SELECT * FROM user_info LIMIT 1")
    abstract fun observe(): Flowable<UserInfoData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(userInfo: UserInfoData): Long

}

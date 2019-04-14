package ru.blizzed.timetablespbu.data.persistance.mappers

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface RxDataMapper<DataType, EntityType> : DataMapper<DataType, EntityType> {

    fun Flowable<List<DataType>>.mapToEntity(): Flowable<List<EntityType>> = map { mapToEntity(it) }

    fun Observable<List<DataType>>.mapToEntity(): Observable<List<EntityType>> = map { mapToEntity(it) }

    fun Single<List<DataType>>.mapToEntity(): Single<List<EntityType>> = map { mapToEntity(it) }

    fun Maybe<List<DataType>>.mapToEntity(): Maybe<List<EntityType>> = map { mapToEntity(it) }


    fun Flowable<List<EntityType>>.mapToData(): Flowable<List<DataType>> = map { mapToData(it) }

    fun Observable<List<EntityType>>.mapToData(): Observable<List<DataType>> = map { mapToData(it) }

    fun Single<List<EntityType>>.mapToData(): Single<List<DataType>> = map { mapToData(it) }

    fun Maybe<List<EntityType>>.mapToData(): Maybe<List<DataType>> = map { mapToData(it) }

}

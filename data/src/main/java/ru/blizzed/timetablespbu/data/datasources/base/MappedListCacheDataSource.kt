package ru.blizzed.timetablespbu.data.datasources.base

import java.util.concurrent.ConcurrentHashMap

open class MappedListCacheDataSource<Key : Any, Value : Any> {

  private val cache: ConcurrentHashMap<Key, List<Value>> = ConcurrentHashMap()

  fun put(key: Key, values: List<Value>) {
    cache[key] = ArrayList(values)
  }

  fun getOrNull(key: Key): List<Value>? = cache[key]

  fun get(key: Key): List<Value> = cache.getValue(key)

}

package ru.blizzed.timetablespbu.extensions

import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable

fun <T> Observable<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(toFlowable(BackpressureStrategy.LATEST))
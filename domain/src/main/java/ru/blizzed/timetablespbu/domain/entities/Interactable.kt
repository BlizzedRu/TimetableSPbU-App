package ru.blizzed.timetablespbu.domain.entities

open class Interactable(
    var isFavorite: Boolean = false,
    isViewed: Boolean = false,
    lastViewTime: Long = 0L
) {

    var lastViewTime: Long = lastViewTime
        private set

    var isViewed: Boolean = isViewed
        private set

    fun view(time: Long) {
        isViewed = true
        lastViewTime = time
    }

}

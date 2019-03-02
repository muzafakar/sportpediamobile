package com.sportpedia.util

object VenueUtil {
    fun parseOpenHour(array: List<Int>): String {
        return "${array[0]}:00 - ${array[1]}:00"
    }

    fun generateOpenHour(array: List<Int>): List<Int> {
        val list = mutableListOf<Int>()
        for (element in array[0]..array[1]) {
            list.add(element)
        }
        return list
    }
}
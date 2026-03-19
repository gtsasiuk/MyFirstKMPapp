package com.example.myfirstkmpapp.data.timezone

interface TimeZoneManager {
    fun getAllTimeZones(): List<String>
    fun currentTime(): String
    fun currentTimeZone(): String
    fun getTimeForZone(timeZoneId: String): String
    fun getDateForZone(timeZoneId: String): String
    fun getTimeDifference(otherTimeZoneId: String): Double
    fun findSuitableHours(timeFrom: Int, timeTo: Int, timeZones: List<String>): List<Int>
    fun isDayTime(timeZoneId: String): Boolean
}
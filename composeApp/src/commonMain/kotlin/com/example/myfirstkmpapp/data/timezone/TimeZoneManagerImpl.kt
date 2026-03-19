package com.example.myfirstkmpapp.data.timezone

import co.touchlab.kermit.Logger
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.offsetAt
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.time.Clock
import kotlin.time.Instant

class TimeZoneManagerImpl : TimeZoneManager {
    private fun formatDateTime(dateTime: LocalDateTime): String {
        val stringBuilder = StringBuilder()
        val minute = dateTime.minute
        var hour = dateTime.hour % 12
        if (hour == 0) hour = 12
        val amPm = if (dateTime.hour < 12) " am" else " pm"
        stringBuilder.append(hour.toString())
        stringBuilder.append(":")
        if (minute < 10) {
            stringBuilder.append('0')
        }
        stringBuilder.append(minute.toString())
        stringBuilder.append(amPm)
        return stringBuilder.toString()
    }

    private fun isValid(timeRange: IntRange, hour: Int,
                        currentTimeZone: TimeZone, otherTimeZone: TimeZone): Boolean {
        if (hour !in timeRange) {
            return false
        }
        val currentUTCInstant: Instant = Clock.System.now()
        val currentOtherDateTime: LocalDateTime = currentUTCInstant.toLocalDateTime(otherTimeZone)
        val otherDateTimeWithHour = LocalDateTime(
            currentOtherDateTime.year,
            currentOtherDateTime.monthNumber,
            currentOtherDateTime.dayOfMonth,
            hour,
            0,
            0,
            0
        )
        val localInstant = otherDateTimeWithHour.toInstant(currentTimeZone)
        val convertedTime = localInstant.toLocalDateTime(otherTimeZone)
        Logger.d("Hour $hour in Time Range ${otherTimeZone.id} is ${convertedTime.hour}")
        return convertedTime.hour in timeRange
    }

    override fun getAllTimeZones(): List<String> {
        return TimeZone.availableZoneIds.sorted()
    }

    override fun currentTime(): String {
        val currentTime: Instant = Clock.System.now()
        val dateTime: LocalDateTime = currentTime.toLocalDateTime(TimeZone.currentSystemDefault())
        return formatDateTime(dateTime)
    }

    override fun currentTimeZone(): String {
        val currentTimeZone = TimeZone.currentSystemDefault().toString()
        return currentTimeZone
    }

    override fun getTimeForZone(timeZoneId: String): String {
        val timeZone = TimeZone.of(timeZoneId)
        val currentTime: Instant = Clock.System.now()
        val dateTime: LocalDateTime = currentTime.toLocalDateTime(timeZone)

        return formatDateTime(dateTime)
    }

    override fun getDateForZone(timeZoneId: String): String {
        val timeZone = TimeZone.of(timeZoneId)
        val currentTime: Instant = Clock.System.now()
        val date: LocalDate = currentTime.toLocalDateTime(timeZone).date

        val day = date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        val month = date.month.name.lowercase().replaceFirstChar { it.uppercase() }

        return "$day, $month ${date.dayOfMonth}"
    }

    override fun getTimeDifference(otherTimeZoneId: String): Double {
        val now = Clock.System.now()
        val currentZone = TimeZone.currentSystemDefault()
        val otherZone = TimeZone.of(otherTimeZoneId)

        val currentOffset = currentZone.offsetAt(now)
        val otherOffset = otherZone.offsetAt(now)

        val differenceInSeconds = currentOffset.totalSeconds - otherOffset.totalSeconds

        return kotlin.math.abs(differenceInSeconds / 3600.0)
    }

    override fun findSuitableHours(startHour: Int, endHour: Int, timeZones: List<String>): List<Int> {
        val timeRange = max(0, startHour)..min(23, endHour)
        val currentZone = TimeZone.currentSystemDefault()

        return timeRange.filter { hour ->
            timeZones.map { TimeZone.of(it) }.filter { it != currentZone }
                .all { zone ->
                    val valid = isValid(timeRange, hour, currentZone, zone)

                    if (valid) {
                        Logger.d("Hour $hour is valid for ${zone.id}")
                    } else {
                        Logger.d("Hour $hour is NOT valid for ${zone.id}")
                    }
                    valid
                }
        }
    }

    override fun isDayTime(timeZoneId: String): Boolean {
        val now = Clock.System.now()
        val currentZone = TimeZone.of(timeZoneId)
        val hour = now.toLocalDateTime(currentZone).hour
        return hour in 6..18
    }
}
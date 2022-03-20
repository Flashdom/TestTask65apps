package com.test.testtask65apps

import android.util.Log
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.*


fun convertServerDateToClientDate(birthday: String): String {
    var rez = ""
    try {
        rez = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).format(
            SimpleDateFormat("yyyy-MM-dd", Locale.ROOT).parse(
                birthday
            ) ?: Date()
        )
    } catch (e: Throwable) {
        Log.d("a", "b")
    }
    return rez
}

fun convertServerDateToAge(birthday: String): String {
    var rez = ""
    try {
        rez = (Instant.now().atZone(ZoneId.systemDefault()).year.minus(
            SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
                .parse(birthday)
                ?.toInstant()
                ?.atZone(ZoneId.systemDefault())?.year ?: 0
        )).toString()
    } catch (e: Throwable) {
        Log.d("a", "b")
    }
    return rez
}

/**
 * Предполагаю, что это ошибка тестовых данных, что начиная с 5 человека, формат даты рождения записан наоборот
 **/
fun convertBackwardsServerDateToClientDate(birthday: String): String {
    var rez = ""
    try {
        rez = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).format(
            SimpleDateFormat("dd-MM-yyyy", Locale.ROOT).parse(
                birthday
            ) ?: Date()
        )
    } catch (e: Throwable) {
        Log.d("a", "b")
    }
    return rez
}

fun convertBackwardsServerDateToAge(birthday: String): String {
    var rez = ""
    try {
        rez = (Instant.now().atZone(ZoneId.systemDefault()).year.minus(
            SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)
                .parse(birthday)
                ?.toInstant()
                ?.atZone(ZoneId.systemDefault())?.year ?: 0
        )).toString()
    } catch (e: Throwable) {
        Log.d("a", "b")
    }
    return rez
}

package com.messon.project.kmovie.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


enum class DateParseType {
  Year, Month, Day, MonthDay
}

object DateFormatter {

  private val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)

  fun extract(dateString: String, parseType: DateParseType = DateParseType.Year) : String {
    return runCatching {
      val date = LocalDate.parse(dateString, inputFormatter)
      when(parseType) {
        DateParseType.Year -> date.year.toString()
        DateParseType.Month -> date.monthValue.toString()
        DateParseType.Day -> date.dayOfMonth.toString()
        DateParseType.MonthDay -> "${date.monthValue}/${date.dayOfMonth}"
      }
    }.getOrElse {
       ""
    }
  }
}
package com.messon.project.kmovie.domain.enum

enum class TimeWindow {
  DAY, WEEK;

  fun lowercaseName(): String = this.name.lowercase()
}
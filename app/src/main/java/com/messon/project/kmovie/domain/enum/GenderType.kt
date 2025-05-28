package com.messon.project.kmovie.domain.enum

enum class GenderType(val code: Int) {
  NOT_SPECIFIED(0),
  FEMALE(1),
  MALE(2),
  NON_BINARY(3);

  companion object {
    fun parser(value: Int) : GenderType {
      return GenderType.entries.find { it.code == value } ?: NOT_SPECIFIED
    }
  }
}


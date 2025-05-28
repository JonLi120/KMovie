package com.messon.project.kmovie.domain.model

import com.messon.project.kmovie.domain.enum.GenderType

data class BasicCelebrityModel(
  val id: Int,
  val adult: Boolean,
  val gender: GenderType,
  val name: String,
  val originalName: String,
  val profileImage: String,
) {
  companion object {
    fun fakeModel() = BasicCelebrityModel(
      id = (1..10000).random(),
      adult = true,
      gender = GenderType.FEMALE,
      name = "河北彩花",
      originalName = "河北彩花",
      profileImage = "/pDVfME2z2DiosnHFBALHvoSvqs5.jpg"
    )
  }
}
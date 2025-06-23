package com.messon.project.kmovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.messon.project.kmovie.data.local.dao.GenreDao
import com.messon.project.kmovie.data.local.entity.GenreEntity

@Database(
  entities = [
    GenreEntity::class,
  ],
  version = 1,
  exportSchema = true,

)
abstract class AppDatabase : RoomDatabase() {
  abstract fun genreDao(): GenreDao
}
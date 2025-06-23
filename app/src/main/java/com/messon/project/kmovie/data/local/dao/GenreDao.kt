package com.messon.project.kmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.messon.project.kmovie.data.local.entity.GenreEntity
import com.messon.project.kmovie.domain.model.GenreModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

  @Insert(onConflict = REPLACE)
  suspend fun insertGenres(genres: List<GenreEntity>)

  @Query("Select * From genres")
  suspend fun getAllGenres(): List<GenreModel>

  @Query("Select count(*) > 0 From genres")
  fun hasGenreData(): Flow<Boolean>
}
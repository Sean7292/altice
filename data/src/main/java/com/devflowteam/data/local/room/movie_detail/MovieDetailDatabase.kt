package com.devflowteam.data.local.room.movie_detail

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devflowteam.data.local.util.Converters

@Database(entities = [MovieDetailEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDetailDatabase: RoomDatabase() {
    abstract fun dao(): MovieDetailDao
}
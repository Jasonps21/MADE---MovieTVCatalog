package com.jason.movietvcatalog.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jason.movietvcatalog.core.data.source.local.entity.ActorEntity
import com.jason.movietvcatalog.core.data.source.local.entity.MovieEntity
import com.jason.movietvcatalog.core.data.source.local.entity.PeopleEntity

@Database(entities = [ActorEntity::class, MovieEntity::class, PeopleEntity::class], version = 1, exportSchema = false)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
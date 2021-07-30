package com.example.core.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core.source.local.entity.SportEntity

@Database(entities = [SportEntity::class], version = 2, exportSchema = false)
abstract class SportDatabase : RoomDatabase() {

    abstract fun sportDao(): SportDao
}
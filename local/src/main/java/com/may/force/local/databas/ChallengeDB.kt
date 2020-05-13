package com.may.force.local.databas

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.may.force.local.model.PeopleInfoDetailsLocal
import com.may.force.local.model.PeopleInfoLocal


@Database(
    entities = [PeopleInfoLocal::class, PeopleInfoDetailsLocal::class],
    version = 1,
    exportSchema = false
)
abstract class ChallengeDB : RoomDatabase() {

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "challengeDB.db"
        @Volatile
        private var INSTANCE: ChallengeDB? = null

        fun getInstance(@NonNull context: Context): ChallengeDB {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            ChallengeDB::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getPeopleInfoDetailsDao(): PeopleInoDetailsDAO

    abstract fun getPeopleInfoDao(): PeopleInoDAO

}


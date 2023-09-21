package online.dailyq.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import online.dailyq.db.dao.UserDao
import online.dailyq.db.entity.UserEntity
import online.dailyq.db.dao.QuestionDao
import online.dailyq.db.entity.QuestionEntity

import android.content.Context
import androidx.room.Room


@Database(
    entities = [
        UserEntity::class,
        QuestionEntity::class
    ], version = 2
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getQuestionDao(): QuestionDao

    companion object {
        const val FILENAME = "dailyq.db"
        @Volatile var INSTANCE: AppDatabase? = null

        private fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                FILENAME
            ).fallbackToDestructiveMigration()
                .build()
        }

        fun getInstance(context: Context): AppDatabase = INSTANCE ?:
        synchronized(this) {
            INSTANCE ?: create(context).also {
                INSTANCE = it
            }
        }
    }
}
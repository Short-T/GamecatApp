package ca.unb.mobiledev.gamecat.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import ca.unb.mobiledev.gamecat.dao.GameDao
import kotlin.jvm.Volatile
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import ca.unb.mobiledev.gamecat.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

/**
 * Database layer on top of the SQLite database
 */
@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    private class GameDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    database.gameDao()
                }
            }
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Singleton access to the database
        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "app_database")
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

package ca.unb.mobiledev.gamecat.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.unb.mobiledev.gamecat.repository.model.Game

/**
 * This DAO object validates the SQL at compile-time and associates it with a method
 */
@Dao
interface GameDao {
    @Query("SELECT * from game_table WHERE name = :name")
    infix fun searchRecords(name : String): List<Game>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(game: Game)

    @Query("SELECT * from game_table order by name ASC")
    fun getAllGames() : List<Game>

    @Query("SELECT COUNT(name) from game_table")
    fun getSize() : Int

// TODO
    //  Add app specific queries in here
    //  Additional details can be found at https://developer.android.com/reference/android/arch/persistence/room/Dao
}
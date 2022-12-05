package ca.unb.mobiledev.gamecat.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ca.unb.mobiledev.gamecat.model.Game

/**
 * This DAO object validates the SQL at compile-time and associates it with a method
 */
@Dao
interface GameDao {
    //@Query("SELECT * from game_table WHERE name = :name")
    //fun searchRecords(name : String): List<Game>
    @Query("SELECT * from game_table")
    fun getAllGames(): LiveData<List<Game>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(game: Game)

    @Delete
    fun delete(game: Game)
}
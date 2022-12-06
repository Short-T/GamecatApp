package ca.unb.mobiledev.gamecat.repository

import android.util.Log
import androidx.lifecycle.LiveData
import ca.unb.mobiledev.gamecat.dao.GameDao
import ca.unb.mobiledev.gamecat.db.AppDatabase
import ca.unb.mobiledev.gamecat.model.Game
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class GameRepository(private val gameDao: GameDao) {
    val gameList: LiveData<List<Game>> = gameDao.getAllGames()

    /*
        Contains functionality:
        gameList - gets all games
        insertGame - inserts one game
        deleteGame - deletes one game
    */

    fun insertGame(name: String?, release: String?, rating: String?,  plat: String?, condition: String?, desc: String?, img: ByteArray?) {
        val newGame = Game()
        newGame.name = name
        newGame.year = release
        newGame.rat = rating
        newGame.plat = plat
        newGame.cond = condition
        newGame.description = desc
        newGame.src = img
        insert(newGame)
    }


    private fun insert(game: Game) {
        gameDao.insert(game)
        val name = game.name
        val release = game.year
        val plat = game.plat
        Log.i("Repo", "Inserting $name, $release, $plat")
    }

    fun deleteGame(game: Game) {
        gameDao.delete(game)
    }

    fun searchGames(name: String): LiveData<List<Game>>? {
        // Using a Callable thread object as there are return values
        val dataReadFuture: Future<LiveData<List<Game>>> = AppDatabase.databaseWriterExecutor.submit(
            Callable {
                gameDao.searchGames("%$name%")
            })
        try {
            while (!dataReadFuture.isDone) {
                // Simulating another task
                TimeUnit.SECONDS.sleep(1)
            }
            return dataReadFuture.get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
            0
        } catch (e: InterruptedException) {
            e.printStackTrace()
            0
        }
        return null
    }

}















package ca.unb.mobiledev.gamecat.repository

import android.util.Log
import androidx.lifecycle.LiveData
import ca.unb.mobiledev.gamecat.dao.GameDao
import ca.unb.mobiledev.gamecat.model.Game

class GameRepository(private val gameDao: GameDao) {
    val gameList: LiveData<List<Game>> = gameDao.getAllGames()

    /*
        Contains functionality:
        gameList - gets all games
        insertGame - inserts one game
        deleteGame - deletes one game

    fun insertGame(name: String?, release: String?, rating: String?,  plat: String?, condition: String?, desc: String?) {
        val newGame = Game()
        newGame.name = name
        newGame.year = release
        newGame.rat = rating
        newGame.plat = plat
        newGame.cond = condition
        newGame.description = desc
        //newGame.src = img
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
    /*fun searchRecord(name: String): List<Item>{
        val dataReadFuture: Future<List<Item>>? = AppDatabase.databaseWriterExecutor.submit(
            Callable{
                itemDao!!searchRecords(name)
            })
        if (dataReadFuture != null) {
            return try{
                while (!dataReadFuture.isDone) {
                    // Simulating another task
                    TimeUnit.SECONDS.sleep(1)
                }
             dataReadFuture.get()

            }catch (e: ExecutionException){
                e.printStackTrace()
                emptyList()
            }catch (e: InterruptedException){
                e.printStackTrace()
                emptyList()
            }

        }
        return emptyList()
    }*/




}















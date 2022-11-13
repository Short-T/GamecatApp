package ca.unb.mobiledev.gamecat.repository

import android.app.Application
import androidx.lifecycle.LiveData
import ca.unb.mobiledev.gamecat.dao.GameDao
import ca.unb.mobiledev.gamecat.db.AppDatabase.Companion.getDatabase
import ca.unb.mobiledev.gamecat.db.AppDatabase
import ca.unb.mobiledev.gamecat.model.Game
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class GameRepository(application: Application) {
    private val itemDao: GameDao? = getDatabase(application).gameDao()

    // TODO Add query specific methods
    //  HINT 1:
    //   The insert operation will use the Runnable interface as there are no return values
    //  HINT 2:
    //   The search operation will use the Callable interface with Future as there are return values
    //  HINT 3:
    //    LiveData does not work as well for this; consider using an object list to return the search results
    //  See the example project file at
    //  https://github.com/hpowell20/cs2063-fall-2022-examples/blob/master/Lecture7/RoomPersistenceLibraryDemo/app/src/main/java/mobiledev/unb/ca/roompersistencetest/repository/ItemRepository.kt
    //  to see examples of how to work with the Executor Service along with Runnables and Callables

    fun insertGame(name: String?, release: String?, condition: String?, desc: String?, img: String?) {
        val newGame = Game()
        newGame.name = name
        newGame.year = release
        newGame.cond = condition
        newGame.description = desc
        newGame.src = img
        insert(newGame)
    }

    private fun insert(game: Game) {
        AppDatabase.databaseWriterExecutor.execute { itemDao!!.insert(game) }

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















package ca.unb.mobiledev.gamecat.utils

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ca.unb.mobiledev.gamecat.db.AppDatabase
import ca.unb.mobiledev.gamecat.repository.GameRepository
import ca.unb.mobiledev.gamecat.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val gameRepo: GameRepository
    val allGames: LiveData<List<Game>>
    var returnedGames: LiveData<List<Game>>?

    init {
        val gameDao = AppDatabase.getDatabase(application, viewModelScope).gameDao()
        gameRepo = GameRepository(gameDao)
        allGames = gameRepo.gameList
        returnedGames = allGames
    }

    fun insert(name: String?, release: String?, rating :String, plat: String, condition: String?, desc: String?, img: ByteArray?) = viewModelScope.launch(Dispatchers.IO){
        gameRepo.insertGame(name, release, rating, plat, condition, desc, img)
        Log.i("view", "Inserting $name, $release, $rating, $condition, $plat, $desc")
    }

    fun delete(game: Game) = viewModelScope.launch (Dispatchers.IO) {
        gameRepo.deleteGame(game)
    }

    fun search(name: String?): LiveData<List<Game>>?{
        var searchResult: LiveData<List<Game>>?
        if (name != null) {
            searchResult = gameRepo.searchGames(name)
        } else {
            searchResult = allGames
        }

        returnedGames = searchResult

        return searchResult
    }



    //fun search(name: String):List<Game>{
    //    return gameRepo.searchRecord(name)
    //}

}
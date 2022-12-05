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

    init {
        val gameDao = AppDatabase.getDatabase(application, viewModelScope).gameDao()
        gameRepo = GameRepository(gameDao)
        allGames = gameRepo.gameList
    }

    fun insert(name: String?, release: String?, rating :String, plat: String, condition: String?, desc: String?) = viewModelScope.launch(Dispatchers.IO){
        gameRepo.insertGame(name, release, rating, plat, condition, desc)
        Log.i("view", "Inserting $name, $release, $rating, $condition, $plat, $desc")
    }




    //fun search(name: String):List<Game>{
    //    return gameRepo.searchRecord(name)
    //}

}
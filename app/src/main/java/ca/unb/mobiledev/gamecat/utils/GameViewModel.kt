package ca.unb.mobiledev.gamecat.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ca.unb.mobiledev.gamecat.repository.GameRepository
import ca.unb.mobiledev.gamecat.repository.model.Game

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val gameRepo: GameRepository = GameRepository(application)
    val allGames: List<Game> = gameRepo.allGames
    val size = gameRepo.size

    // TODO
    //  Add mapping calls between the UI and Database

    fun insert(name: String?, release: String?, plat: String, condition: String?, desc: String?){
        gameRepo.insertGame(name, release, plat, condition, desc)
    }



    //fun search(name: String):List<Game>{
    //    return gameRepo.searchRecord(name)
    //}

}
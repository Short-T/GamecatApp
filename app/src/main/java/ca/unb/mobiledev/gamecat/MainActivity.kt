package ca.unb.mobiledev.gamecat

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ca.unb.mobiledev.gamecat.model.Game
import ca.unb.mobiledev.gamecat.repository.GameRepository
import ca.unb.mobiledev.gamecat.utils.GameAdapter
import ca.unb.mobiledev.gamecat.utils.GameViewModel
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    //private var test: Button? = null
    private lateinit var viewModel: GameViewModel
    private lateinit var adapterG: GameAdapter
    private lateinit var aTest: ImageButton
    private lateinit var searchButton: ImageButton
    private lateinit var mListView: ListView
    private lateinit var searchTool: Toolbar
    private lateinit var edit: EditText
    private lateinit var confirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        var mDataSet: LiveData<List<Game>> = viewModel.allGames

        mListView = findViewById(R.id.listView)
        this.viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        viewModel.allGames.observe(this) {
            games ->
            if(games != null) {
                adapterG = GameAdapter(applicationContext, games)
                mListView.adapter = adapterG
            }
            adapterG.notifyDataSetChanged()
        }

        // TODO tap listView to start activity_detail

        mListView.onItemClickListener =
            AdapterView.OnItemClickListener { _: AdapterView<*>, _: View, position: Int, _: Long ->
                val game: Game? = viewModel.allGames.value?.get(position)
                if (game != null) {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("title", game.name)
                    intent.putExtra("platform", game.plat)
                    intent.putExtra("rating", game.rat)
                    intent.putExtra("description", game.description)
                    intent.putExtra("year", game.year)
                    intent.putExtra("condition", game.cond)
                    intent.putExtra("image", game.src)
                    Log.i("Main", "")
                    try {
                        ContextCompat.startActivity(this@MainActivity, intent, null)
                    } catch (e: ActivityNotFoundException) {
                        e.printStackTrace()
                    }
                }
                true
            }
            
        mListView.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { _: AdapterView<*>, _: View, position: Int, _: Long ->
                val game: Game? = viewModel.allGames.value?.get(position)
                if (game !=null){
                    viewModel.delete(game)
                }
                true

            }
                /*mListView.setOnClickListener {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("title", game.name)
                    intent.putExtra("platform", game.plat)
                    intent.putExtra("description", game.description)
                    intent.putExtra("image", game.src)

                    try {
                        ContextCompat.startActivity(parentActivity, intent, null)
                    } catch (e: ActivityNotFoundException) {
                        e.printStackTrace()
                    }
                }*/

        aTest = findViewById<View>(R.id.deleteButton) as ImageButton
        aTest!!.setOnClickListener {
            val addIntent = Intent(this@MainActivity, AddActivity::class.java)
            try {
                startActivity(addIntent)
            } catch (ex: ActivityNotFoundException) {
                Log.e("Main", "Unable to start the activity")
            }
        }

        searchTool = findViewById<View>(R.id.searchToolbar) as Toolbar
        edit = findViewById<View>(R.id.editSearch) as EditText
        confirm = findViewById<View>(R.id.confirmButton) as Button
        searchButton = findViewById<View>(R.id.searchButton) as ImageButton

        searchButton.setOnClickListener {
            if(searchTool.visibility == View.GONE) {
                searchTool.visibility = View.VISIBLE
            } else {
                searchTool.visibility = View.GONE
            }
        }
        confirm.setOnClickListener {
            val searchText = edit.text.toString()
            val returnedGames: LiveData<List<Game>>? = viewModel.search(searchText)
            if(returnedGames != null) {
                viewModel.returnedGames?.observe(this) { games ->
                    if(games != null) {
                        adapterG = GameAdapter(applicationContext, games)
                        mListView.adapter = adapterG
                    }
                    adapterG.notifyDataSetChanged()
                }
            } else {
                Toast.makeText(this, "No search results", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
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
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ca.unb.mobiledev.gamecat.repository.model.Game
import java.util.ArrayList
class MainActivity : AppCompatActivity() {
    //private var test: Button? = null
    private var aTest: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView3)

        //test = findViewById<View>(R.id.button) as Button
        aTest = findViewById<View>(R.id.addButton) as ImageButton
        //Sample code for button, will delete later
        /*test!!.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                Log.e("Main", "Unable to start the activity")
            }
        }*/
        aTest!!.setOnClickListener {
            val addIntent = Intent(this@MainActivity, AddActivity::class.java)
            try {
                startActivity(addIntent)
            } catch (ex: ActivityNotFoundException) {
                Log.e("Main", "Unable to start the activity")
            }
        }
    }

    class MyAdapter(private val mDataset: ArrayList<Game>, private val parentActivity: Activity) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {
        // ViewHolder represents an individual item to display. In this case
        // it will just be a single TextView (displaying the title of a course)
        // but RecyclerView gives us the flexibility to do more complex things
        // (e.g., display an image and some text).
        class ViewHolder(v: TextView) : RecyclerView.ViewHolder(v){
            var mGamePhoto: ImageView = v.findViewById(R.id.image)//Check if image is the right ID later
            var mTitleTextView: TextView = v.findViewById(R.id.title)
            var mPlatformTextView: TextView = v.findViewById(R.id.platform)
            var mYearTextView: TextView = v.findViewById(R.id.releaseYear)
        }



        // The inflate method of the LayoutInflater class can be used to obtain the
        // View object corresponding to an XML layout resource file. Here
        // onCreateViewHolder inflates the TextView corresponding to item_layout.xml
        // and uses it to instantiate a ViewHolder.
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false) as TextView
            return ViewHolder(v)
        }

        // onBindViewHolder binds a ViewHolder to the data at the specified
        // position in mDataset
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val game = mDataset[position]
            holder.mTitleTextView.text = game.title
            holder.mPlatformTextView.text = game.platform
            holder.mYearTextView.text = game.year
            holder.mGamePhoto//need to figure out the game
            // TODO
            // Add other views in viewholder to accompany title
            //

            holder.mTitleTextView.setOnClickListener {
                val intent = Intent(parentActivity, DetailActivity::class.java)
                intent.putExtra("title", game.title)
                intent.putExtra("description", game.description)
                intent.putExtra("image", game.src)

                try {
                    ContextCompat.startActivity(parentActivity, intent, null)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }

            }
        }
        override fun getItemCount(): Int {
            return mDataset.size
        }
    }
}
package ca.unb.mobiledev.gamecat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Get intents
        val title = intent.getStringExtra("title")
        val plat = intent.getStringExtra("platform")
        //val year = intent.getStringExtra("year")
        val description = intent.getStringExtra("description")

        // Get textviews
        val tText = findViewById<TextView>(R.id.gameNameBox)
        val pText = findViewById<TextView>(R.id.platformBox)
        val yText = findViewById<TextView>(R.id.releaseBox)
        val rText = findViewById<TextView>(R.id.ratingBox)
        val dText = findViewById<TextView>(R.id.notesBox)

        // Set textviews
        tText.text = title
        pText.text = plat
        //yText.text = year
        //rText.text = rat
        dText.text = description


        // TODO 3
        //  Make the TextView scrollable
        //  HINT: Look at the movementMethod attribute for descTextView

        //textView.movementMethod = ScrollingMovementMethod()

        // TODO 4 ( I DONT THINK WE NEED TO DO THIS)
        //  Set the title of the action bar to be the course title
        //  HINT:
        //   This might help you - http://developer.android.com/reference/android/support/v7/app/AppCompatActivity.html#getSupportActionBar%28%29
    }
}
package ca.unb.mobiledev.gamecat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO 1
        //  Get the intent that started this activity, and get the extras from it
        //  description + rating + condition + release etc

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")

        //val textView = findViewById<TextView>(R.id.description_textview)
        // TODO 2
        //  Set the description TextView to be the course description

        //textView.text = description

        // TODO 3
        //  Make the TextView scrollable
        //  HINT: Look at the movementMethod attribute for descTextView

        //textView.movementMethod = ScrollingMovementMethod()

        // TODO 4
        //  Set the title of the action bar to be the course title
        //  HINT:
        //   This might help you - http://developer.android.com/reference/android/support/v7/app/AppCompatActivity.html#getSupportActionBar%28%29
    }
}
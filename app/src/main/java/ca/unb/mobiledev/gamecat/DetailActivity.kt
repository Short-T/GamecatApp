package ca.unb.mobiledev.gamecat

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.room.TypeConverter

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Get intents
        val title = intent.getStringExtra("title")
        val plat = intent.getStringExtra("platform")
        val rat = intent.getStringExtra("rating")
        val year = intent.getStringExtra("year")
        val cond = intent.getStringExtra("condition")
        val description = intent.getStringExtra("description")
        val src = intent.getByteArrayExtra("image")


        // Get textviews
        val tText = findViewById<TextView>(R.id.gameNameBox)
        val pText = findViewById<TextView>(R.id.platformBox)
        val yText = findViewById<TextView>(R.id.releaseBox)
        val cText = findViewById<TextView>(R.id.conditionBox)
        val rText = findViewById<TextView>(R.id.ratingBox)
        val dText = findViewById<TextView>(R.id.notesBox)

        val iImageView = findViewById<ImageView>(R.id.imageView)

        Log.i("Detail", "$title $plat $rat $year $cond $description")
        // Set textviews
        tText.text = title
        pText.text = plat
        yText.text = year
        cText.text = cond
        rText.text = rat
        dText.text = description

        val imageSrc: ByteArray? = src
        val imageBit: Bitmap? = imageSrc?.let { toBitmap(it) }
        iImageView.setImageBitmap(imageBit)

        // TODO 3
        //  Make the TextView scrollable
        //  HINT: Look at the movementMethod attribute for descTextView

        //textView.movementMethod = ScrollingMovementMethod()

        // TODO 4 ( I DONT THINK WE NEED TO DO THIS)
        //  Set the title of the action bar to be the course title
        //  HINT:
        //   This might help you - http://developer.android.com/reference/android/support/v7/app/AppCompatActivity.html#getSupportActionBar%28%29
        // TODO Delete function

    }

    //Taken from here https://github.com/prasadankitt/ImageApp/blob/main/app/src/main/java/com/example/cameragallery/Converter.kt
    @TypeConverter
    fun toBitmap(byteArray: ByteArray) : Bitmap {
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }

}
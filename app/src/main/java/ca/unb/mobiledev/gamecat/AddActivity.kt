package ca.unb.mobiledev.gamecat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ca.unb.mobiledev.gamecat.utils.KeyboardUtils

class AddActivity : AppCompatActivity() {
    private var editTextTitle: EditText? = null
    private var editTextRelease: EditText? = null
    private var editTextCond: EditText? = null
    private var editTextNotes: EditText? = null
    private var editTextRating: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        editTextTitle = findViewById(R.id.textInputName)
        editTextRelease = findViewById(R.id.textInputRelease)
        editTextCond = findViewById(R.id.textInputCond)
        editTextNotes = findViewById(R.id.textInputNotes)
        editTextRating = findViewById(R.id.textInputRating)

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            if(TextUtils.isEmpty(editTextTitle!!.text.toString())) {
                Toast.makeText(applicationContext, "Can't create a game without a title", Toast.LENGTH_SHORT).show();
            } else {
                // TODO What it does to add a game
                // It should add the game to the database

                //finish() should terminate the activity
                finish()
            }
        }
    }
}
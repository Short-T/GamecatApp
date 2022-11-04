package ca.unb.mobiledev.gamecat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.EditText
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


    }
}
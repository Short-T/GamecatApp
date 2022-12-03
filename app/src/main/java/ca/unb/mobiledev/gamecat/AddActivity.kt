package ca.unb.mobiledev.gamecat

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class AddActivity : AppCompatActivity() {
    private var editTextTitle: EditText? = null
    private var editTextRelease: EditText? = null
    private var editTextCond: EditText? = null
    private var editTextNotes: EditText? = null
    private var editTextRating: EditText? = null
    private var gameButton: ImageButton? = null


    @SuppressLint("IntentReset")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        editTextTitle = findViewById(R.id.textInputName)
        editTextRelease = findViewById(R.id.textInputRelease)
        editTextCond = findViewById(R.id.textInputCond)
        editTextNotes = findViewById(R.id.textInputNotes)
        editTextRating = findViewById(R.id.textInputRating)

        gameButton = findViewById(R.id.imageButton)
        gameButton!!.setOnClickListener{
            //Opens option field to select if photo is from
            //Gallery or camera
            selectImage()
        }

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

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            400 -> if (resultCode === RESULT_OK) {
                val selectedImage: Uri? = data?.data

                //Sets the button using an URI
                // gameButton?.setImageURI(selectedImage)

                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
                gameButton?.setImageBitmap(bitmap)
            }
            500 -> if (resultCode === RESULT_OK) {
                val bitmap: Bitmap = data?.extras?.get("data") as Bitmap
                gameButton?.setImageBitmap(bitmap)

            }
        }


    }

    private fun selectImage() {
        val options = arrayOf<CharSequence>("Take New Photo", "Choose Existing Photo From Gallery", "Cancel")
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Select an Option:")
        builder.setItems(options, DialogInterface.OnClickListener { dialog, item ->
            if (options[item] == "Take New Photo") {
                dialog.dismiss()
                var cameraIntent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, 500)
            }
            else if (options[item] == "Choose Existing Photo From Gallery") {
                dialog.dismiss()
                var intent: Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 400)
            }
            else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        }).show()

    }
}



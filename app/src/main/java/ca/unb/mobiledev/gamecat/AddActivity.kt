package ca.unb.mobiledev.gamecat

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.TypeConverter
import ca.unb.mobiledev.gamecat.utils.GameViewModel
import java.io.ByteArrayOutputStream
import java.util.*


class AddActivity : AppCompatActivity() {
    private var editTextTitle: EditText? = null
    private var editPlatform: EditText? = null
    private var editTextRelease: EditText? = null
    private var editTextCond: EditText? = null
    private var editTextNotes: EditText? = null
    private var editTextRating: EditText? = null
    private var gameButton: ImageButton? = null

    private var testImage: Bitmap? = null
    private var image: ByteArray? = null

    private lateinit var viewModel: GameViewModel

    @SuppressLint("IntentReset")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        //example in RoomPersistenceLab MainActivity
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]

        editTextTitle = findViewById(R.id.textInputName)
        editPlatform = findViewById(R.id.textInputPlatform)//adding to xml rn
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
                Toast.makeText(applicationContext, "Can't create a game without a title", Toast.LENGTH_SHORT).show()
            } else {

                //Converts Bitmap to Bytearray for insert
                try {
                    val bitmap: Bitmap? = testImage
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap!!.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)
                    val bytesImage = byteArrayOutputStream.toByteArray()


                    //insert name, release, plat, cond, desc
                    viewModel.insert(
                        editTextTitle!!.text.toString(),
                        editTextRelease!!.text.toString(),
                        editTextRating!!.text.toString(),
                        editPlatform!!.text.toString(),
                        editTextCond!!.text.toString(),
                        editTextNotes!!.text.toString(), bytesImage
                    )
                    //finish() should terminate the activity
                    finish()
                } catch(e: Exception) {
                    Toast.makeText(applicationContext, "Can't create a game without an image", Toast.LENGTH_LONG).show()
                }
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
                testImage = bitmap
                gameButton?.setImageBitmap(bitmap)
                image = fromBitmap(bitmap)
            }
            500 -> if (resultCode === RESULT_OK) {
                val bitmap: Bitmap = data?.extras?.get("data") as Bitmap
                testImage = bitmap
                gameButton?.setImageBitmap(bitmap)
                image = fromBitmap(bitmap)
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
    //Taken from here https://github.com/prasadankitt/ImageApp/blob/main/app/src/main/java/com/example/cameragallery/Converter.kt
    @TypeConverter
    fun toBitmap(byteArray: ByteArray) : Bitmap {
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap) : ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream)
        return outputStream.toByteArray()
    }
}



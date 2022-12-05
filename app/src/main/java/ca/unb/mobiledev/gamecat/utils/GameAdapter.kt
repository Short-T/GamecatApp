package ca.unb.mobiledev.gamecat.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ca.unb.mobiledev.gamecat.R
import ca.unb.mobiledev.gamecat.model.Game
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.room.TypeConverter

class GameAdapter(context: Context, games: List<Game>) : ArrayAdapter<Game>(
    context, 0, games) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val game = getItem(position)

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false)
        }

        // TODO
        // Add images
        val image = view!!.findViewById<ImageView>(R.id.GameIconImage)

        val title = view!!.findViewById<TextView>(R.id.title)
        val desc = view.findViewById<TextView>(R.id.platform)
        val rel = view.findViewById<TextView>(R.id.releaseYear)

        title.text = game!!.name
        desc.text = game.plat
        rel.text = game.year

        val imageSrc: ByteArray? = game.src
        val imageBit: Bitmap? = imageSrc?.let { toBitmap(it) }
        image.setImageBitmap(imageBit)

        return view
    }

    //Taken from here https://github.com/prasadankitt/ImageApp/blob/main/app/src/main/java/com/example/cameragallery/Converter.kt
    @TypeConverter
    fun toBitmap(byteArray: ByteArray) : Bitmap {
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }

}
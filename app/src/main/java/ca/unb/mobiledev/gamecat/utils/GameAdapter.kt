package ca.unb.mobiledev.gamecat.utils

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ca.unb.mobiledev.gamecat.R
import ca.unb.mobiledev.gamecat.model.Game
import org.w3c.dom.Text

class GameAdapter(context: Context, games: List<Game>) : ArrayAdapter<Game>(
    context, 0, games) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val game = getItem(position)

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.game_list, parent, false)
        }

        val title = view!!.findViewById<TextView>(R.id.name)
        val desc = view.findViewById<TextView>(R.id.description)
        //val pic = game?.src
        //val plat = view.findViewById<TextView>(R.id.platform)

        title.text = game!!.name
        desc.text = game.description
        //pic = game.src
        //plat.text = game.plat

        return view
    }
}
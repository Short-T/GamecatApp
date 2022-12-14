package ca.unb.mobiledev.gamecat.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
class Game{
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name: String? = null
    var plat: String? = null
    var description: String? = null
    var year: String? = null
    var rat: String? = null
    // TODO add rating
    //might not be string for img
    var src: ByteArray? = null
    var cond: String? = null

    /**data class Builder(
        var id: String? = null,
        var name: String? = null,
        var description: String? = null,
        var src: String? = null,
        var cond: String? = null
    ) {

        fun id(id: String) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun description(description: String) = apply { this.description = description }
        fun src(src: String) = apply { this.src = src}
        fun cond(cond: String) = apply { this.cond = cond}
        fun build() = Game(name, description, src, cond)
    }*/
}

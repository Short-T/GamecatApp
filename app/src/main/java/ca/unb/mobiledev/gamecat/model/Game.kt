package ca.unb.mobiledev.gamecat.model

import android.media.Image

class Game private constructor(
    private val id: String?,
    private val name: String?,
    val description: String?,
    val src: String?,
) {
    // Only need to include getters
    val title: String
        get() = "$id: $name"

    data class Builder(
        var id: String? = null,
        var name: String? = null,
        var description: String? = null,
        var src: String? = null,
    ) {

        fun id(id: String) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun description(description: String) = apply { this.description = description }
        fun src(src: String) = apply { this.src = src}

        fun build() = Game(id, name, description, src)
    }
}

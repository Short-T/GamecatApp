package ca.unb.mobiledev.gamecat.model

class Game private constructor(
    private val id: String?,
    private val name: String?,
    val description: String?,
    val src: String?,
    val cond: String?
) {
    // Only need to include getters
    val title: String
        get() = "$name"

    val condition: String
        get() = "Condition: $cond"

    data class Builder(
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
        fun build() = Game(id, name, description, src, cond)
    }
}

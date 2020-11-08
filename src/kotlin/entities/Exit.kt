package com.ericharm
import com.googlecode.lanterna.TextColor

class Exit(override var location: Point) : Entity(location) {
    companion object {
        val color = TextColor.ANSI.GREEN
    }

    override val character = ColorChar('X', color)
}

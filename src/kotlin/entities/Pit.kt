package com.ericharm
import com.googlecode.lanterna.TextColor

class Pit(override var location: Point) : Entity(location) {
    companion object {
        val color = TextColor.ANSI.YELLOW
    }

    override val character = ColorChar('^', color)
}

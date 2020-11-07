package com.ericharm
import com.googlecode.lanterna.TextColor

class Pit(override var location: Point) : Entity(location) {
    override val character = ColorChar('^', TextColor.ANSI.YELLOW)
}

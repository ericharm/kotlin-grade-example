package com.ericharm
import com.googlecode.lanterna.TextColor

class Target(override var location: Point) : Entity(location) {
    override val character = ColorChar('^', TextColor.ANSI.YELLOW)
}

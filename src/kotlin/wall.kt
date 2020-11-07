package com.ericharm
import com.googlecode.lanterna.TextCharacter

class Wall(override var location: Point) : Entity(location) {
    override val character = TextCharacter('#')
}

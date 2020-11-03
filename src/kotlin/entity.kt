package com.ericharm
import com.googlecode.lanterna.graphics.TextGraphics

open class Entity(open var location: Pair<Int, Int>) {
    val x: Int
        get() = location.first
    val y: Int
        get() = location.second
    open val character = '?'

    fun render(graphics: TextGraphics) {
        graphics.putString(x, y, character.toString())
    }

    fun move(x: Int, y: Int) {
        // first check for boundaries
        // next find colliding entities and run onCollisionWith(entity)
        // if the square is empty even after running onCollision, update location
        location = Pair(this.x + x, this.y + y)
    }
}

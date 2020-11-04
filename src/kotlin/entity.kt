package com.ericharm
import com.googlecode.lanterna.graphics.TextGraphics
import kotlin.random.*

open class Entity(open var location: Point) {
    val x: Int
        get() = location.x
    val y: Int
        get() = location.y
    open val character = '?'

    fun render(graphics: TextGraphics) {
        graphics.putString(x, y, character.toString())
    }

    fun move(x: Int, y: Int) {
        location = Point(this.x + x, this.y + y)
    }

    fun moveThroughLevel(level: Level, x: Int, y: Int): Boolean {
        val newLocation = Point(this.x + x, this.y + y)
        if (newLocation.inLevel(level)) {
            val collidingEntities = level.entities.filter { it.x == newLocation.x && it.y == newLocation.y }
            if (collidingEntities.size == 0) {
                move(x, y)
                return true
            }
            else {
                collidingEntities.forEach {
                    if (it is Boulder) {
                        if (it.moveThroughLevel(level, x, y)) move(x, y)
                    }
                }
            }
        }
        return false
    }
}

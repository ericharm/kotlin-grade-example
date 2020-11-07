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

    fun moveTo(x: Int, y: Int) {
        location = Point(x, y)
    }

    open fun onCollidesWith(entities: List<Entity>, level: Level, vector: Point): Boolean {
        return false
    }

    open fun moveThroughLevel(level: Level, vector: Point): Boolean {
        val newLocation = Point(x + vector.x, y + vector.y)
        if (newLocation.inLevel(level)) {
            val collidingEntities = level.entities.filter { it.x == newLocation.x && it.y == newLocation.y }
            if (collidingEntities.size == 0) {
                move(vector.x, vector.y)
                return true
            } else return onCollidesWith(collidingEntities, level, vector)
        }
        return false
    }
}

package com.ericharm
import com.googlecode.lanterna.graphics.TextGraphics
import kotlin.random.*

open class Entity(open var location: Point) {
    val x: Int
        get() = location.x
    val y: Int
        get() = location.y
    open val character = '?'

    val uuid = Random.nextInt()

    override fun equals(other: Any?): Boolean {
        return other is Entity && this.uuid == other.uuid
    }

    override fun hashCode(): Int = uuid

    fun render(graphics: TextGraphics) {
        graphics.putString(x, y, character.toString())
    }

    fun move(x: Int, y: Int) {
        location = Point(this.x + x, this.y + y)
    }

    fun moveThroughLevel(level: Level, x: Int, y: Int) {
        val newLocation = Point(this.x + x, this.y + y)
        val inHorizontalBounds  = newLocation.x > 0 && newLocation.x < level.width - 1
        val inVerticalBounds = newLocation.y > 0 && newLocation.y < level.height - 1
        if (inHorizontalBounds && inVerticalBounds) {
            val collidingEntities = level.entities.filter { it != this && it.x == newLocation.x && it.y == newLocation.y }
            if (collidingEntities.size == 0) move(x, y)
        }
    }
}

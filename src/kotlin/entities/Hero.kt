package com.ericharm
import com.googlecode.lanterna.TextColor

class Hero(override var location: Point) : Entity(location) {
    companion object {
        val color = TextColor.ANSI.MAGENTA
    }

    override val character = ColorChar('@', color)

    override open fun onCollidesWith(entities: List<Entity>, level: Level, vector: Point): Boolean {
        entities.forEach {
            if (it is Boulder && it.moveThroughLevel(level, vector)) {
                move(vector.x, vector.y)
                return true
            } else if (it is Exit) App.swapCurrentState(Victory())
        }
        return false
    }
}

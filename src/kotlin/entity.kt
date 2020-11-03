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
        location = Pair(this.x + x, this.y + y)
        // destinedTile = level.tiles[location.first][location.second]
        // later we maybe use tiles to check the destinedTile for a list
        // of entities, but for now we'll just loop all the entities in
        // the level
        // collidingEntities = level.entities.filter
    }
}

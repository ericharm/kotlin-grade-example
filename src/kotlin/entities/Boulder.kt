package com.ericharm
import com.googlecode.lanterna.TextColor

class Boulder(override var location: Point) : Entity(location) {
    override val character = ColorChar('0', TextColor.ANSI.CYAN)

    override fun onCollidesWith(entities: List<Entity>, level: Level, vector: Point): Boolean {
        entities.forEach {
            if (it is Pit) {
                listOf(this, it).forEach { entity: Entity -> level.pluckEntity(entity) }
                return true
            }
        }
        return false
    }
}

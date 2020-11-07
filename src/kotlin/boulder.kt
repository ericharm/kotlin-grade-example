package com.ericharm

class Boulder(override var location: Point) : Entity(location) {
    override val character = '0'

    override open fun onCollidesWith(entities: List<Entity>, level: Level, vector: Point): Boolean {
        entities.forEach {
            if (it is Target) {
                listOf(this, it).forEach { entity: Entity -> level.pluckEntity(entity) }
                return true
            }
        }
        return false
    }
}

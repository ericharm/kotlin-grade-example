package com.ericharm

class Wall(override var location: Point) : Entity(location) {
    override val character = '#'
}

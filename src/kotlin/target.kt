package com.ericharm

class Target(override var location: Point) : Entity(location) {
    override val character = '.'
}

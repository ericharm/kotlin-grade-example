package com.ericharm
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.input.KeyType

class Level (val width: Int, val height: Int) {
    object hero : Entity(Point(10, 10)) {
        override val character = '@'
    }

    val boulders = listOf(Boulder(Point(2,3)), Boulder(Point(8, 12)))
    val entities = listOf(hero) + boulders

    fun render(graphics: TextGraphics) {
        hero.render(graphics)
        boulders.forEach { it.render(graphics) }
    }

    fun handleInput(key: KeyType) {
        val direction = hashMapOf(
            KeyType.ArrowDown to Point(0, 1), KeyType.ArrowUp to Point(0, -1),
            KeyType.ArrowLeft to Point(-1, 0), KeyType.ArrowRight to Point(1, 0)
        )[key]
        if (direction != null) {
            val x = direction.x
            val y = direction.y
            hero.moveThroughLevel(this, x, y)
            // boulders.forEach { if (it.x == hero.x && it.y == hero.y) it.move(x, y) }
        }
    }

    fun update() {
    }
}

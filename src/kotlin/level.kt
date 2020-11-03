package com.ericharm
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.input.KeyType

class Level () {
    object boulder : Entity(Pair(12, 13)) {
        override val character = '0'
    }

    object hero : Entity(Pair(10, 10)) {
        override val character = '@'
    }

    val boulders = listOf(boulder)

    fun render(graphics: TextGraphics) {
        hero.render(graphics)
        boulders.forEach { it.render(graphics) }
    }

    fun handleInput(key: KeyType) {
        val direction = hashMapOf(
            KeyType.ArrowDown to Pair(0, 1),
            KeyType.ArrowUp to Pair(0, -1),
            KeyType.ArrowLeft to Pair(-1, 0),
            KeyType.ArrowRight to Pair(1, 0)
        )[key]
        if (direction != null) {
            val x = direction!!.first
            val y = direction!!.second
            hero.move(x, y)
            val collidingBoulders = boulders.filter { it.x != hero.x && it.y != hero.y }
            boulders.forEach { if (it.x == hero.x && it.y == hero.y) it.move(x, y) }
        }
    }

    fun update() {
    }
}

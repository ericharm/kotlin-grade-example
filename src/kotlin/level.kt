package com.ericharm
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.input.KeyType

class Level () {
    object boulder : Entity(Pair(12, 13)) {
        override val character = '0'
    }

    object hero : Entity(Pair(10, 10)) {
        override val character = '@'

        fun handleInput(key: KeyType) {
            val direction = hashMapOf(
                KeyType.ArrowDown to Pair(0, 1),
                KeyType.ArrowUp to Pair(0, -1),
                KeyType.ArrowLeft to Pair(-1, 0),
                KeyType.ArrowRight to Pair(1, 0)
            )[key]
            if (direction != null) move(direction!!.first, direction!!.second)
        }
    }

    val entities = listOf(boulder, hero)

    fun render(graphics: TextGraphics) {
        entities.forEach { it.render(graphics) }
    }

    fun handleInput(key: KeyType) {
        hero.handleInput(key)
    }

    fun update() {
    }
}

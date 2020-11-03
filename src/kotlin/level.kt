package com.ericharm
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.input.KeyType

class Level () {
    // tiles - a 2d array representing x and y coordinates of level,
    //         in which to store entities and walls, etc.
    // entities - a list in which to store the hero, boulders, and targets

    object boulder : Entity(Pair(12, 13)) {
        override val character = '0'
    }

    object hero : Entity(Pair(10, 10)) {
        override val character = '@'

        fun handleInput(key: KeyType) {
            if (key == KeyType.ArrowDown) move(Pair(0, 1))
            if (key == KeyType.ArrowUp) move(Pair(0, -1))
            if (key == KeyType.ArrowLeft) move(Pair(-1, 0))
            if (key == KeyType.ArrowRight) move(Pair(1, 0))
        }
    }

    fun render(graphics: TextGraphics) {
        hero.render(graphics)
        boulder.render(graphics)
    }

    fun handleInput(key: KeyType) {
        hero.handleInput(key)
    }

    fun update() {
    }
}
